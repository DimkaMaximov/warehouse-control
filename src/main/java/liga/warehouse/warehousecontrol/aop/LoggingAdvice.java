package liga.warehouse.warehousecontrol.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.warehouse.warehousecontrol.model.LogEntity;
import liga.warehouse.warehousecontrol.repository.LogEntityRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAdvice {

    @Autowired
    LogEntityRepository repository;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within (liga.warehouse.warehousecontrol.controller.*)")
    public void controllersPc() {
    }

    @Around("controllersPc()")
    public Object controllersLogging(ProceedingJoinPoint pjp)  throws JsonProcessingException {

        LocalDateTime eventTime = LocalDateTime.now();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();

        ObjectMapper mapper = new ObjectMapper();
        Object[] array = pjp.getArgs();
        String args = mapper.writeValueAsString(array);

        LogEntity logEntity = LogEntity.builder()
                .eventTime(eventTime)
                .methodName(methodName)
                .className(className)
                .args(args)
                .build();

        repository.insert(logEntity);
        Long eventId = repository.findLogId(eventTime);
        log.info("Log#{} {} Вызван метод: {}: {}{}.", eventId, eventTime, className, methodName, args);

        Object object = null;
        try {
            object = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return object;
    }
}