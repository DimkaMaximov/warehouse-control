package liga.warehouse.warehousecontrol.repository;

import liga.warehouse.warehousecontrol.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductRepository {

    List<Product> findAll();

    Product findById(Long productId);

    List<Product> findAllByProductType(Long productType);

    int insertAll(@Param("productList") List<Product> productList);

    int insert(Product product);

    boolean updateById(@Param("product") Product product);

    boolean deleteById(@Param("productId") Long productId);
}