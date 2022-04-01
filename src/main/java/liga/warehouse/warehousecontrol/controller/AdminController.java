package liga.warehouse.warehousecontrol.controller;

import liga.warehouse.warehousecontrol.api.ProductService;
import liga.warehouse.warehousecontrol.dto.ProductDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("admin/product")
public class AdminController {

    private final ProductService service;

    public AdminController(ProductService service) {
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

    @GetMapping("/type/{id}")
    List<ProductDto> getProductTypes(@PathVariable Long id) {
        return service.findAllByProductType(id);
    }

    @PostMapping("/save-all")
    void saveNewProducts(@RequestBody @Valid List<ProductDto> productDtoList) {
        service.insertAll(productDtoList);
    }

    @PostMapping("/save")
    void saveNewProduct(@RequestBody @Valid ProductDto productDto) {
        service.insert(productDto);
    }

    @PostMapping("/{id}/remove")
    void deleteProduct(@PathVariable Long id) {
        service.deleteById(id);
    }

    //купить или занести товар в корзину в количестве amount
    @PostMapping("/{id}/sale/{amount}")
    void saleProduct(@PathVariable Long id, @PathVariable Long amount) {
        service.saleById(id, amount);
    }
}