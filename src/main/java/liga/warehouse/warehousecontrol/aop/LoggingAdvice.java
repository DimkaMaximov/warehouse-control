package liga.warehouse.warehousecontrol.aop;

import liga.warehouse.warehousecontrol.model.LogEntity;
import liga.warehouse.warehousecontrol.model.UserEntity;
import liga.warehouse.warehousecontrol.repository.LogEntityRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Pointcut("execution(* org.springframework.security.core.userdetails.UserDetailsService.loadUserByUsername(String))")
    public void userDetailsServicePc() {
    }

    @Around("controllersPc()")
    public Object controllersLogging(ProceedingJoinPoint pjp) {

        LocalDateTime eventTime = LocalDateTime.now();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();

        UserEntity user = (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        LogEntity logEntity = LogEntity.builder()
                .eventTime(eventTime)
                .methodName(methodName)
                .className(className)
                .args(user.getEmail())
                .build();

        repository.insert(logEntity);
        Long eventId = repository.findLogId(eventTime);
        log.info("Log#{} {} Вызван метод: {}: {} пользователем {}.", eventId, eventTime, className, methodName, user.getEmail());

        Object object = null;
        try {
            object = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return object;
    }

    @Around("userDetailsServicePc()")
    public Object authenticationLogging(ProceedingJoinPoint pjp) {

        LocalDateTime eventTime = LocalDateTime.now();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        String email = array[0].toString();

        LogEntity logEntity = LogEntity.builder()
                .eventTime(eventTime)
                .className(className)
                .methodName(methodName)
                .args(email)
                .build();

        repository.insert(logEntity);
        Long eventId = repository.findLogId(logEntity.getEventTime());

        Object object = null;
        try {
            object = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (object != null) {
            log.info("Log#{} {} Аутентификация с email: {} завершена успешно", eventId, logEntity.getEventTime(), logEntity.getArgs());
        } else
            log.info("Log#{} {} Ошибка аутентификации. Аккаунт с email: {} не зарегистрирован", eventId, logEntity.getEventTime(), logEntity.getArgs());

        return object;
    }
}