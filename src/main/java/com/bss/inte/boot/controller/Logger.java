package com.bss.inte.boot.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;

public class Logger {
	/**
	 * This is the main method which contains logging streaming , so here we change
	 * then we can apply to all other
	 * 
	 * @param str
	 */
	public static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);
	
	public static void write(String str) {
		writeWithTime(str);
	}
	
	private static void writeStream(String msg) {
		logger.info(msg);
	}

	public static void write(String timeStamp, String message) {
		writeStream(timeStamp + " : " + message);
	}

	public static void writeWithTime(String message) {
		writeStream(getTime() + " ::: " + message);
	}

	public static String getTime() {
		Date date = new Date(System.currentTimeMillis());
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		return formatter.format(date).toString();
	}
}
