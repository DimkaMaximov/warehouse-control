package liga.warehouse.warehousecontrol.repository;

import liga.warehouse.warehousecontrol.model.LogEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import java.time.LocalDateTime;

@Mapper
public interface LogEntityRepository {

    @Insert("insert into log_events (event_time, method_name, class_name, args) " +
            "values(#{eventTime}, #{methodName}, #{className}, #{args}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(LogEntity logEntity);

    @Select("select id from log_events where event_time = #{eventTime}")
    long findLogId(LocalDateTime eventTime);
}