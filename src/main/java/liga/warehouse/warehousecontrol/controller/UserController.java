package liga.warehouse.warehousecontrol.controller;

import liga.warehouse.warehousecontrol.api.ProductService;
import liga.warehouse.warehousecontrol.dto.ProductDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("user/product")
public class UserController {

    private final ProductService service;

    public UserController(ProductService service) {
        this.service = service;
    }

    @GetMapping("")
    List<ProductDto> getProducts() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    ProductDto getProductById(@PathVariable Long id) {
        return service.findById(id);
    }

    //получить номенклатуру товара по его типу (Техника, Продукты, Стройматериалы, Одежда, Ювелирка)
    @GetMapping("/type/{id}")
    List<ProductDto> getProductTypes(@PathVariable Long id) {
        return service.findAllByProductType(id);
    }

    //купить или занести товар в корзину в количестве amount
    @PostMapping("/{id}/buy/{amount}")
    void saleProduct(@PathVariable Long id, @PathVariable Long amount) {
        service.saleById(id, amount);
    }

}