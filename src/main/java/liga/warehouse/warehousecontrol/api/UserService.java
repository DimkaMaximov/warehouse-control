package liga.warehouse.warehousecontrol.api;

import liga.warehouse.warehousecontrol.dto.UserEntityDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends AbstractService<UserEntityDto>, UserDetailsService {

    UserEntityDto findByEmail(String email);
}