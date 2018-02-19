package com.epam.project.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.epam.project.dao")
@PropertySource(value = {"classpath:db.properties"})
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        Properties properties = connectionProperties();
        dataSource.setDriverClassName(properties.getProperty("driverClassName"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setInitialSize(Integer.parseInt(properties.getProperty("initialSize")));
        dataSource.setMaxTotal(Integer.parseInt(properties.getProperty("maxPoolSize")));
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setPackagesToScan("com.epam.project.entity");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        localSessionFactoryBean.setDataSource(getDataSource());
        localSessionFactoryBean.afterPropertiesSet();
        return localSessionFactoryBean.getObject();
    }

    private Properties connectionProperties() {
        Properties properties = new Properties();
        properties.put("driverClassName", environment.getRequiredProperty("hibernate.connection.driver_class"));
        properties.put("url", environment.getRequiredProperty("hibernate.connection.url"));
        properties.put("username", environment.getRequiredProperty("hibernate.connection.username"));
        properties.put("password", environment.getRequiredProperty("hibernate.connection.password"));
        properties.put("initialSize", environment.getRequiredProperty("hibernate.connection.initialSize"));
        properties.put("maxPoolSize", environment.getRequiredProperty("hibernate.connection.maxPoolSize"));

        return properties;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
}
