package liga.warehouse.warehousecontrol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    Long id;

    String productName;

    Long productType;

    String productCod;

    String country;

    Long supplierId;

    String unit;

    Long amount;

    Double price;
}