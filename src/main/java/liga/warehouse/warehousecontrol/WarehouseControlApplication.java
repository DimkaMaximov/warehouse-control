package liga.warehouse.warehousecontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseControlApplication {

    /**
     * after init() you can login as:
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