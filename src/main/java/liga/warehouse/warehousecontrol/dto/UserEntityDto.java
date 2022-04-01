package liga.warehouse.warehousecontrol.dto;

import liga.warehouse.warehousecontrol.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDto {

    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Role role;
}