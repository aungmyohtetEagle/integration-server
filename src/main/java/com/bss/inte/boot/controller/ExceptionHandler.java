package com.bss.inte.boot.controller;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import com.bss.inte.boot.domain.InteData;

public class ExceptionHandler {
	/* Exception Type */
	public int BATCH_EX_INSERT = 10;
	public int BATCH_EX_UPDATE = 11;
	public int SQL_EX_INSERT = 20;
	public int SQL_EX_UPDATE = 21;
	public int GENERAL_EXCEPTION = 30;

	/* Key Type Values , Type Definitions */
	public String TYPE_INT = "int";
	public String TYPE_STR = "String";
	public String TYPE_LONG = "long";

	public static void process(int exceptionType, Exception e) {

	}

	/**
	 * Update status of INT_MIFOS_JOURNAL Table
	 * 
	 * @param dataSource
	 * @param exceptionType
	 * @param entityName
	 * @param keyTypeValues
	 * @param e
	 */
	public static void logForINT_MIFOS_JOURNAL(DataSource dataSource, List<InteData> batchList, List<Long> errorRows) {

	}

	public static void logIntoFiles(Exception e) {

	}

	public static List<Integer> writeExceptionAndGetFailedIndexes(Exception e) {
		List<Integer> failedIndexList = new ArrayList<>();
		if (e instanceof BatchUpdateException) {
			int[] updatedCount = ((BatchUpdateException) e).getUpdateCounts();
			Logger.write("-- Failed counts -- " + Arrays.toString(updatedCount));
			for (int j = 0; j < updatedCount.length; j++) {
				if (updatedCount[j] < 0) {
					failedIndexList.add(j);
				}
			}
		} else {
			Logger.writeWithTime(e.getLocalizedMessage());
		}
		return failedIndexList;
	}
}
