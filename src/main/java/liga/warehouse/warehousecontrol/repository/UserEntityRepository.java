package liga.warehouse.warehousecontrol.repository;

import liga.warehouse.warehousecontrol.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserEntityRepository {

    List<UserEntity> findAll();

    UserEntity findById(Long id);

    int insert(UserEntity user);

    boolean updateById(@Param("user") UserEntity user);

    boolean deleteById(@Param("userId") Long userId);

    UserEntity findByEmail(String email);
}