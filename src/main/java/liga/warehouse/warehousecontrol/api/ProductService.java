package liga.warehouse.warehousecontrol.api;

import liga.warehouse.warehousecontrol.dto.ProductDto;
import java.util.List;

public interface ProductService extends AbstractService<ProductDto> {

    List<ProductDto> findAllByProductType(Long productTypeId);

    void insertAll(List<ProductDto> productList);

    void saleById(Long id, Long amount);
}