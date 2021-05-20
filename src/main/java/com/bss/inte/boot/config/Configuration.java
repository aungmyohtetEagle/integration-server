package com.bss.inte.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.bss.inte.boot.controller.Logger;

@org.springframework.context.annotation.Configuration
@PropertySource(value = "classpath:META-INF/spring/jdbc.properties")
public class Configuration {
	
	@Autowired
	Environment env;
	
	@Bean
    public DataSource postgresDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty("erp_driver"));
        dataSourceBuilder.url(env.getProperty("erp_url"));
        dataSourceBuilder.username(env.getProperty("erp_usrn"));
        dataSourceBuilder.password(env.getProperty("erp_pass"));
        Logger.write(env.getProperty("erp_url"));
        Logger.write("p-build....");
        return dataSourceBuilder.build();
    }
	
	@Bean
    public DataSource mysqlDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty("mifos_driver"));
        dataSourceBuilder.url(env.getProperty("mifos_url"));
        dataSourceBuilder.username(env.getProperty("mifos_usrn"));
        dataSourceBuilder.password(env.getProperty("mifos_pass"));
        Logger.write("mysql-build....");
        return dataSourceBuilder.build();
    }
	
	public void loadConfiguration() {
		
	}
	

}
