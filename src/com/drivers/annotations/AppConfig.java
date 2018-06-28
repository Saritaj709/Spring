package com.drivers.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("com.annotation")
@PropertySource("classpath:oracledb.properties")
public class AppConfig {

	@Autowired
	Environment environment;
	@Bean
	DataBaseDriver oracleDriver() {
		OracleDriver oracleDriver=new OracleDriver();
		oracleDriver.setDriver(environment.getProperty("db.driver"));
		oracleDriver.setUrl(environment.getProperty("db.url"));
		oracleDriver.setPort(Integer.parseInt(environment.getProperty("db.port")));
		oracleDriver.setUser(environment.getProperty("db.user"));
		oracleDriver.setPassword(environment.getProperty("db.password"));
		return oracleDriver;
	}
	@Bean
	DataBaseDriver mySqlDriver() {
		return new MySqlDriver();
	}
}
