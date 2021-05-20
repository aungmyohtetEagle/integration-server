package com.bss.inte.boot.data.service;

import java.sql.Connection;
import java.util.List;

public interface IntegrationDataWritingService {
	
	public void updateProcessId(Long processId);

	public void updateSuccessData(Long processId);

	void updateFailedData(List<Integer> failedIndexList, Connection conn);

	public void insertData(int numberOfRecordsToBeExecuteEachTime);

}
