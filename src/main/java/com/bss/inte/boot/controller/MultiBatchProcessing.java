package com.bss.inte.boot.controller;

import java.util.List;

import com.bss.inte.boot.data.service.IntegrationDataWritingService;
import com.bss.inte.boot.data.service.IntegrationDataWritingServiceImpl;
import com.bss.inte.boot.domain.InteData;

public class MultiBatchProcessing implements MultiBatchProcessingInf {

	private String batchName = "No Defined Batch Name";
	private MultiBatchController batchController;

	IntegrationDataWritingService writeService;

	public void init(MultiBatchController batchController, List<InteData> computedBatchList) {
		this.batchController = batchController;
		this.batchName = "T_" + System.currentTimeMillis(); // TODO generate Thread Name
		this.writeService = new IntegrationDataWritingServiceImpl(batchController.getSourceDataSource(),
				batchController.getDestinationDataSource(), computedBatchList);
	}

	public void run() {
		Logger.writeWithTime(batchName + "==================== Starting =====================");
		MultiBatchThreadCounter threadCounter = batchController.getThreadCounter();
		threadCounter.increase();
		this.writeService.insertData(batchController.getNumberOfRecordsToBeExecuteEachTime());
		threadCounter.decrease();

		// If the current thread is the last thread, it must update all data's status as
		// success except for the failed ones.
		if (threadCounter.isLastFinishingThread()) {
			this.writeService.updateSuccessData(batchController.getProcessId());
		}
		Logger.writeWithTime(batchName + "++++++++++++++++++++++++ End +++++++++++++++++++++++");
	}

}
