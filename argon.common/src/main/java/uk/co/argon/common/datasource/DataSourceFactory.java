package uk.co.argon.common.datasource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceFactory {
    
    @Bean(name = "oracleDS")
    public static DataSource oracleDS() throws FileNotFoundException, IOException {
        BasicDataSource ds = new BasicDataSource();
        Properties props = new Properties();
        props.load(DataSourceFactory.class.getClassLoader().getResourceAsStream("application.properties"));
        
        ds.setDriverClassName(props.getProperty("spring.datasource.driver-class-name"));
        ds.setUrl(props.getProperty("spring.datasource.url"));
        ds.setUsername(props.getProperty("spring.datasource.username"));
        ds.setPassword(props.getProperty("spring.datasource.password"));
        
        return ds;
    }
}
