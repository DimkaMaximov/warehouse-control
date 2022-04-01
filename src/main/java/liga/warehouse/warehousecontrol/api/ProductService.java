package liga.warehouse.warehousecontrol.api;

import liga.warehouse.warehousecontrol.dto.ProductDto;
import liga.warehouse.warehousecontrol.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();

    ProductDto findById(Long productId);

    List<ProductDto> findAllByProductType(Long productTypeId);

    void insertAll(List<ProductDto> productList);

    void insert(ProductDto product);

    void deleteById(Long productId);

    void saleById(Long id, Long amount);
}