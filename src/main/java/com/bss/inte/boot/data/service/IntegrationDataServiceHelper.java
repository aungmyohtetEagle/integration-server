package com.bss.inte.boot.data.service;

import com.bss.inte.boot.utils.GlobalUtility;

public class IntegrationDataServiceHelper {
	
	// can export to .properties file
	private static final String sourceTableName = "mifos_integration";
	private static final String destiTableName = "int_erp_journal";
	//private static final String destiTableName = "mifos_integration_temp";
	
	// send_erp status
	public static final int SEND_ERP_UNSEND = 0;
	public static final int SEND_ERP_SUCCESS = 1;
	public static final int SEND_ERP_FAIL = 2;
	
	public static Long generateProcessId() {
		return Long.valueOf(GlobalUtility.generateDate("yyyyMMddHHmmss"));
	}
	
	public static String retrieveSQL(Long processId) {
		return new StringBuffer()
			.append("SELECT * FROM ")
			.append(sourceTableName)
			.append(" WHERE process_id = ")
			.append(processId)
			.toString();
	}
	
	private static String getUnsentOrFailedStatusWithProcessIDNullWhereClause() {
		return new StringBuffer()
				.append(" WHERE send_erp IN ( ")
				.append(SEND_ERP_UNSEND)
				.append(", ")
				.append(SEND_ERP_FAIL)
				.append(" ) ")
				.append("AND process_id IS NULL ")
				.toString();
	}
	
	public static String insertPSQL() {
		return new StringBuffer()
				.append("INSERT INTO ")
				.append(destiTableName)
				.append(" ( item_id, item_header_id, posting_date, reference, branch_id, currency, gl_code, journal_code, label, credit, debit, cust_id, ")
				.append("loan_acct, savings_acct, transaction_type, send_erp )")
				.append(" VALUES (?,?,?"
						+ "::date"
						+ ",?,?,?,?,?,?,?,?,?,?,?,?,?)")
				.toString();
	}
	
	public static String updateGenearatedProcessIDForAllUnsentOrFailedDataWithProcessIdNullPSQL() {
		return new StringBuffer()
				.append("UPDATE ")
				.append(sourceTableName)
				.append(" SET process_id = ? ")
				.append(getUnsentOrFailedStatusWithProcessIDNullWhereClause())
				.toString();
	}
	
	public static String updateStatusToFailedDataPSQL() {
		return new StringBuffer()
				.append("UPDATE ")
				.append(sourceTableName)
				.append(" SET send_erp = " + SEND_ERP_FAIL)
				.append(" ,process_id = null")
				.append(" WHERE item_id = ? and item_header_id = ? ")
				.toString();
	}
	
	public static String updateStatusToSuccessDataPSQL() {
		return new StringBuffer()
				.append("UPDATE ")
				.append(sourceTableName)
				.append(" SET send_erp = " + SEND_ERP_SUCCESS)
				.append(" ,process_id = null")
				.append(" WHERE process_id = ? ")
				.toString();
	}
	
	
	

}
