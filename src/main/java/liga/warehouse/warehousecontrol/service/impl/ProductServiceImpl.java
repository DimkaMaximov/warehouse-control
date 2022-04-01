package liga.warehouse.warehousecontrol.service.impl;

import liga.warehouse.warehousecontrol.api.ProductService;
import liga.warehouse.warehousecontrol.dto.ProductDto;
import liga.warehouse.warehousecontrol.model.Product;
import liga.warehouse.warehousecontrol.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ModelMapper modelMapper;

    ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> productList = repository.findAll();
        return productList.stream()
                .map(el -> modelMapper.map(el, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long productId) {
        Product product = repository.findById(productId);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> findAllByProductType(Long productTypeId) {
        List<Product> productTypeList = repository.findAllByProductType(productTypeId);
        return productTypeList.stream()
                .map(el -> modelMapper.map(el, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void insertAll(List<ProductDto> productDtoList) {
        List<Product> productList = productDtoList.stream()
                .map(el -> modelMapper.map(el, Product.class))
                .collect(Collectors.toList());
        repository.insertAll(productList);
    }

    @Override
    public void insert(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        if (product.getId() == null) repository.insert(product);
        else repository.updateById(product);
    }

    @Override
    public void deleteById(Long productId) {
        repository.deleteById(productId);
    }

    @Override
    public void saleById(Long id, Long amount) {
        repository.saleById(id, amount);
    }
}