package com.bss.inte.boot.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalUtility {
	
	public static String generateDate(String format) {
		Date date = new Date(System.currentTimeMillis());
		DateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date); 
	}

}
