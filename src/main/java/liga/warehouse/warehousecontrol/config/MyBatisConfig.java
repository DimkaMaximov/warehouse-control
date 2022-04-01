package liga.warehouse.warehousecontrol.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@Slf4j
public class MyBatisConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        return getDataSource();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private DataSource getDataSource() {

        log.info("Setting up datasource for {} environment.", env.getActiveProfiles());

        DataSource dataSource = DataSourceBuilder.create()
                .url(env.getProperty("spring.datasource.url"))
                .username(env.getProperty("spring.datasource.username"))
                .password(env.getProperty("spring.datasource.password"))
                .build();
        String maxPoolSize = env.getProperty("spring.datasource.hikari.maximum-pool-size");
        assert maxPoolSize != null;
        ((HikariDataSource) dataSource)
                .setMaximumPoolSize(Integer.parseInt(maxPoolSize));

        return dataSource;
    }
}