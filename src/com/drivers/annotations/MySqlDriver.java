package com.drivers.annotations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mysqldb.properties")
public class MySqlDriver implements DataBaseDriver {

	@Value("${databaseName}")
	private String databaseName;
	@Value("${disableStatementPooling")
	private String disableStatementPooling;
	
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "[ Driver: mySql "+", databaseName: "+databaseName+" ,disableStatementPooling: "+disableStatementPooling+"]";
	}

}
