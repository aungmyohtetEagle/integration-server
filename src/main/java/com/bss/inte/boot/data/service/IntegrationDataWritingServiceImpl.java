package com.bss.inte.boot.data.service;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bss.inte.boot.controller.ExceptionHandler;
import com.bss.inte.boot.controller.Logger;
import com.bss.inte.boot.domain.InteData;

public class IntegrationDataWritingServiceImpl implements IntegrationDataWritingService {

	private DataSource destincationDataSource;
	private DataSource sourceDataSource;
	private List<InteData> batchList;

	public IntegrationDataWritingServiceImpl(DataSource sourceDataSource, DataSource destincationDataSource,
			List<InteData> batchList) {
		this.sourceDataSource = sourceDataSource;
		this.destincationDataSource = destincationDataSource;
		this.batchList = batchList;
	}

	@Override
	public void insertData(int numberOfRecordsToBeExecuteEachTime) {
		try (Connection conn = destincationDataSource.getConnection()) {
		    
			PreparedStatement stmt = conn.prepareStatement(IntegrationDataServiceHelper.insertPSQL());
			int i = 0;
			for (InteData data : batchList) {
				stmt.setLong(1, data.getItemId());
				stmt.setString(2, data.getItemHeaderId());
				stmt.setString(3, data.getPostingDate());
				stmt.setString(4, data.getReference());
				stmt.setLong(5, data.getBranchId());
				stmt.setString(6, data.getCurrency());
				stmt.setInt(7, data.getGlCode());
				stmt.setString(8, data.getJournalCode());
				stmt.setString(9, data.getLabel());
				stmt.setDouble(10, data.getCredit());
				stmt.setDouble(11, data.getDebit());
				stmt.setLong(12, data.getCustId());
				stmt.setString(13, data.getLoanAcct());
				stmt.setString(14, data.getSavingsAcct());
				stmt.setInt(15, 1); // TODO Transaction_Type is int / varchar
				stmt.setInt(16, data.getSendErp());
				stmt.addBatch();
				
				i++;
				if (i % numberOfRecordsToBeExecuteEachTime == 0 || i == batchList.size()) {
					try {
						//conn.setAutoCommit(false);
						Logger.write("Auto_commit" + stmt.getConnection().getAutoCommit());
						stmt.executeBatch();
						// conn.commit();	
					} catch (SQLException e) {
						Logger.write("____________Inner_Sql_Exception___________");
						
						Logger.write(((BatchUpdateException) e).getLargeUpdateCounts().length +"");
						Logger.write(((BatchUpdateException) e).getUpdateCounts().length + "");
						
						Logger.write("LUC" + Arrays.toString(((BatchUpdateException) e).getLargeUpdateCounts()));
						Logger.write("UC" + Arrays.toString(((BatchUpdateException) e).getUpdateCounts()));
						e.printStackTrace();
						List<Integer> failedIndexList = ExceptionHandler.writeExceptionAndGetFailedIndexes(e);
						this.updateFailedData(failedIndexList, conn);
					}

				}
				
			}
		} catch (SQLException e) {
			Logger.write("____________Outter_Sql_Exception___________");
			e.printStackTrace();

		}
	}

	@Override
	public void updateFailedData(List<Integer> failedIndexList, Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(IntegrationDataServiceHelper.updateStatusToFailedDataPSQL());
			int i = 0;
			for (Integer index : failedIndexList) {
				stmt.setLong(1, this.batchList.get(index).getItemId());
				stmt.setString(2, this.batchList.get(index).getItemHeaderId());
				stmt.addBatch();
				i++;
				if (i % 1000 == 0 || i == failedIndexList.size()) {
					stmt.executeBatch();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateSuccessData(Long processId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.sourceDataSource);
		jdbcTemplate.update(IntegrationDataServiceHelper.updateStatusToSuccessDataPSQL(), new Object[] { processId });
	}

	@Override
	public void updateProcessId(Long processId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.sourceDataSource);
		jdbcTemplate.update(
				IntegrationDataServiceHelper.updateGenearatedProcessIDForAllUnsentOrFailedDataWithProcessIdNullPSQL(),
				new Object[] { processId });
	}
	
	public void handleException() {
		
	}
	
	

}
