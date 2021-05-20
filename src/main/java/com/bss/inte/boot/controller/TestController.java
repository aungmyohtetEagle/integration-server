package com.bss.inte.boot.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bss.inte.boot.config.Configuration;
import com.bss.inte.boot.config.PropertyProvider;
import com.bss.inte.boot.data.service.IntegrationDataRetrievingService;
import com.bss.inte.boot.data.service.IntegrationDataRetrievingServiceImpl;
import com.bss.inte.boot.data.service.IntegrationDataServiceHelper;
import com.bss.inte.boot.data.service.IntegrationDataWritingService;
import com.bss.inte.boot.data.service.IntegrationDataWritingServiceImpl;
import com.bss.inte.boot.domain.InteData;

@RestController
public class TestController {

	@Autowired
	@Qualifier("mysqlDataSource")
	DataSource sourceDataSource;

	@Autowired
	@Qualifier("postgresDataSource")
	DataSource destinationDataSource;
	
	@Autowired
	PropertyProvider propProvider;

	@GetMapping("/test")
	public String connectionTest() {
		
		MultiBatchController batchController = new MultiBatchController(sourceDataSource, propProvider, destinationDataSource);
		batchController.executeBatch();
		return "success";

	}

	public void getTime(String msg) {
		Date date = new Date(System.currentTimeMillis());
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		System.out.println(msg + "---" + formatter.format(date) + "---");
	}
	
	@GetMapping("/testProp")
	public String testProp() {
		Logger.write("testing");
		return "";
	}
	
	

}
