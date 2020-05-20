package com.sula.resources;

import com.sula.dao.BookDAO;
import com.sula.dao.daoimpl.BookDAOImpl;
import com.sula.dao.daoimpl.MemberDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.sula")
//@PropertySource(value = { "classpath:application.properties" },ignoreResourceNotFound=true) //If the property file is not found, then we will get FileNotFoundException. Sometimes we don’t want to throw exception because our application can work with default values too
public class AppConfig {

   /* @Bean("meberdaoimpl")
    public MemberDAOImpl memberdaoimpl(){
        return new MemberDAOImpl();
    }*/

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        /*DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));*/

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/library");
        dataSource.setUsername("root");
        dataSource.setPassword("");


        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }



}
