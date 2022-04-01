package liga.warehouse.warehousecontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@Component
public class InitAccount {

    /**
     * after init() you can login as:
     * owner    owner
     * admin    admin
     * user1    password
     * user2    password
     * user3    password
     */

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void init() {

        jdbcTemplate.execute("INSERT INTO user_entity VALUES ('1', 'owner', '$2a$10$l7jEpifXJqvgZtBS5P3DoOOEeb.SUU5dH2tH/VUXlaM4TFTOSodki', 'Чак', 'Норрис', '+73281768123', 'ROLE_OWNER')");

        jdbcTemplate.execute("INSERT INTO user_entity VALUES ('2', 'admin', '$2a$10$g0bEuUplrpNsL/mWYUgrjeHUHH1Rz6wlSXqL.RxQujZPomK046kaK', 'Арнольд', 'Шварценеггер', '+74324329824', 'ROLE_ADMIN')");

        jdbcTemplate.execute("INSERT INTO user_entity VALUES ('3', 'user1', '$2a$10$5cn9RhtgWKO12gkgFIMB9uMu2HVY87v4ueS1P1xUJxpKkJD1sMw8u', 'Сильвестр', 'Сталлоне', '+7234877243', 'ROLE_USER')");
        jdbcTemplate.execute("INSERT INTO user_entity VALUES ('4', 'user2', '$2a$10$5cn9RhtgWKO12gkgFIMB9uMu2HVY87v4ueS1P1xUJxpKkJD1sMw8u', 'Брюс', 'Уиллис', '+732342743', 'ROLE_USER')");
        jdbcTemplate.execute("INSERT INTO user_entity VALUES ('5', 'user3', '$2a$10$5cn9RhtgWKO12gkgFIMB9uMu2HVY87v4ueS1P1xUJxpKkJD1sMw8u', 'Джеки', 'Чан', '+732878128', 'ROLE_USER')");
    }

    @PreDestroy
    public void delete() {

        jdbcTemplate.execute("DELETE FROM user_entity WHERE id = 1");
        jdbcTemplate.execute("DELETE FROM user_entity WHERE id = 2");
        jdbcTemplate.execute("DELETE FROM user_entity WHERE id = 3");
        jdbcTemplate.execute("DELETE FROM user_entity WHERE id = 4");
        jdbcTemplate.execute("DELETE FROM user_entity WHERE id = 5");
    }
}