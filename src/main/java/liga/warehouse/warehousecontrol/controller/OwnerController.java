package liga.warehouse.warehousecontrol.controller;

import liga.warehouse.warehousecontrol.api.UserService;
import liga.warehouse.warehousecontrol.dto.UserEntityDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("owner")
public class OwnerController {

    private final UserService service;

    public OwnerController(UserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    List<UserEntityDto> getUsers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    UserEntityDto getUserById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/save")
    void saveNewUser(@RequestBody @Valid UserEntityDto userDto) {
        service.insert(userDto);
    }

    @PostMapping("/{id}/remove")
    void deleteUser(@PathVariable Long id) {
        service.deleteById(id);
    }
}