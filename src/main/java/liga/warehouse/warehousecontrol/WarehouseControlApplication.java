package liga.warehouse.warehousecontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseControlApplication {

    /**
     * 1. Before start app, create and connect "warehouse" database
     * 2. Execute create_schema.sql in resources
     *
     * http://localhost:8090/
     *
     * you can login as:
     *
     * owner    owner
     * admin    admin
     * user1    password
     * user2    password
     * user3    password
     */

    public static void main(String[] args) {
        SpringApplication.run(WarehouseControlApplication.class, args);
    }
}