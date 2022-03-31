package liga.warehouse.warehousecontrol.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

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