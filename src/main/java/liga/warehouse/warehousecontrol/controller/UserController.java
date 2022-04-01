package liga.warehouse.warehousecontrol.controller;

import liga.warehouse.warehousecontrol.api.ProductService;
import liga.warehouse.warehousecontrol.api.UserService;
import liga.warehouse.warehousecontrol.dto.ProductDto;
import liga.warehouse.warehousecontrol.dto.UserEntityDto;
import liga.warehouse.warehousecontrol.model.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/info")
    UserEntityDto getInfo(@AuthenticationPrincipal UserEntity user) {
        return userService.findById(user.getId());
    }

    @GetMapping("/product")
    List<ProductDto> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/product/{id}")
    ProductDto getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    //получить номенклатуру товара по его типу (Техника, Продукты, Стройматериалы, Одежда, Ювелирка)
    @GetMapping("/product/type/{id}")
    List<ProductDto> getProductTypes(@PathVariable Long id) {
        return productService.findAllByProductType(id);
    }

    //купить или занести товар в корзину в количестве amount
    @PostMapping("/product/{id}/buy/{amount}")
    void saleProduct(@PathVariable Long id, @PathVariable Long amount) {
        productService.saleById(id, amount);
    }

}