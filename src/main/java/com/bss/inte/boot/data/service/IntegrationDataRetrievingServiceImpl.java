package com.bss.inte.boot.data.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bss.inte.boot.domain.InteData;

public class IntegrationDataRetrievingServiceImpl implements IntegrationDataRetrievingService{

	private DataSource dataSource;
	
	public IntegrationDataRetrievingServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<InteData> retrieveDataWithProcessID(Long processId) {
		
		List<InteData> list = new JdbcTemplate(this.dataSource).query(IntegrationDataServiceHelper.retrieveSQL(processId),
				(rs, rowNum) -> new InteData(
						rs.getLong("item_id"),
						rs.getString("item_header_id"),
						rs.getString("posting_date"),
						rs.getString("reference"),
						rs.getLong("branch_id"),
						rs.getString("currency"),
						rs.getInt("gl_code"),
						rs.getString("journal_code"),
						rs.getString("label"),
						rs.getDouble("credit"),
						rs.getDouble("debit"),
						rs.getLong("cust_id"),
						rs.getString("loan_acct"),
						rs.getString("savings_acct"),
						rs.getString("transaction_type"),
						rs.getInt("send_erp"),
						rs.getString("record_date")
						)
				);
		return list;
	}

}
