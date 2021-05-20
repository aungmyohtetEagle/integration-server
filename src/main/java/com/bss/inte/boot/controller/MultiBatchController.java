package com.bss.inte.boot.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.bss.inte.boot.config.PropertyProvider;
import com.bss.inte.boot.data.service.IntegrationDataRetrievingServiceImpl;
import com.bss.inte.boot.data.service.IntegrationDataServiceHelper;
import com.bss.inte.boot.data.service.IntegrationDataWritingServiceImpl;
import com.bss.inte.boot.domain.InteData;

public class MultiBatchController {

	private int maxNumberOfThreads = 10;
	private int numberOfRecordsToBeExecuteEachTime = 1000;
	private Long MAX_NUM_OF_RECORDS_ASSIGNED_TO_THREAD = 20000L;

	List<InteData> sourceBatchList = new ArrayList<>();

	private MultiBatchThreadCounter threadCounter = new MultiBatchThreadCounter();

	private Long processId;

	private PropertyProvider propProvider;

	private DataSource sourceDataSource;

	private DataSource destinationDataSource;

	public MultiBatchController(DataSource sourceDataSource, PropertyProvider propProvider,
			DataSource destinationDataSource) {
		/*
		 * @grant 2021-05-02 Get Each Number From Configuration File or System
		 * Configuration Parameters so Here , you write for getting each configuration
		 * info from external source , No Configuration In Program/Coding
		 */
		this.sourceDataSource = sourceDataSource;
		this.destinationDataSource = destinationDataSource;
		this.propProvider = propProvider;
		init(propProvider);

	}

	private void init(PropertyProvider propProvider) {

		/*
		 * @grant 2021-05-02 Get Each Number From Configuration File or System
		 * Configuration Parameters so Here , you write for getting each configuration
		 * info from external source , No Configuration In Program/Coding
		 */

		maxNumberOfThreads = propProvider.getMaxNumberOfBatchThreads();
		numberOfRecordsToBeExecuteEachTime = propProvider.getNumberOfRecordsToPreparedBatchStatement();
		MAX_NUM_OF_RECORDS_ASSIGNED_TO_THREAD = propProvider.getMaxNumberOfRecordsAssignedToThread();

	}

	public void executeBatch() {
		this.processId = IntegrationDataServiceHelper.generateProcessId();
		updateProcessIdToUnsendData();
		this.sourceBatchList = retrieveDataByProcessId();
		if (!this.sourceBatchList.isEmpty()) {
			List<List<InteData>> separatedBatchList = getBatchListByCalculation(sourceBatchList);
			List<Thread> createdBatchThreads = createBatchProcessors(separatedBatchList);
			executeBatchThreads(createdBatchThreads);
		}
	}

	private List<InteData> retrieveDataByProcessId() {
		return new IntegrationDataRetrievingServiceImpl(sourceDataSource).retrieveDataWithProcessID(this.processId);
	}

	private void updateProcessIdToUnsendData() {
		new IntegrationDataWritingServiceImpl(sourceDataSource, null, null).updateProcessId(processId);
	}

	/*
	 * Create Batch Threads Based on the Batch List and Batch Size in Configuration
	 * 
	 */
	private List<Thread> createBatchProcessors(List<List<InteData>> separatedBatchList) {
		if (sourceBatchList == null)
			return null;

		List<Thread> runnableProcessor = new ArrayList<>();
		for (int i = 0; i < separatedBatchList.size(); i++) {
			try {
				Thread createdThread = createProcessor(separatedBatchList.get(i));
				runnableProcessor.add(createdThread);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return runnableProcessor;
	}

	/*
	 * Execute Batch Thread one by one
	 * 
	 * 
	 */
	private void executeBatchThreads(List<Thread> runnableProcessor) {
		// add logging and exception handling
		for (int i = 0; i < runnableProcessor.size(); i++) {
			runnableProcessor.get(i).start();
		}

	}

	/*
	 * Create Batch Thread one by one
	 * 
	 * 
	 */
	private Thread createProcessor(List<InteData> batchList) throws Exception, ClassNotFoundException,
			InvocationTargetException, IllegalAccessException, NoSuchMethodException {

		// In the future, we can use interface rather than clear definition of class ..
		// as below
		// Class<?> c = Class.forName(NAME_MULTI_BATCH_CLASS);
		// Constructor<?> cons = c.getConstructor(MultiBatchProcessingInf.class);
		// MultiBatchProcessingInf batchProcessor =
		// (MultiBatchProcessingInf)cons.newInstance(null);

		MultiBatchProcessingInf batchProcessor = new MultiBatchProcessing();
		batchProcessor.init(this, batchList);
		return new Thread(batchProcessor);
	}

	/*
	 * Create Batch List , from original batch list, divided by
	 * MAX_NUM_OF_BATCH_THREADS Count
	 * 
	 */
	private List<List<InteData>> getBatchListByCalculation(List<InteData> batchList) {
		/*
		 * @grant 2021-05-02 Need to have exception handling and logging to see
		 * 
		 * @Eagle 2021-05-07 Fixing each block to contain exact data
		 */
		int adder = 0;
		int leftover = 0;
		int startPoint = 0;
		int endPoint = 0;
		adder = batchList.size() / maxNumberOfThreads;
		leftover = batchList.size() % maxNumberOfThreads;
		endPoint = adder + leftover;

		List<List<InteData>> allBatchSubList = new ArrayList<List<InteData>>();

		for (int i = 0; i < maxNumberOfThreads; i++) {
			allBatchSubList.add(batchList.subList(startPoint, endPoint));
			startPoint = endPoint;
			endPoint += adder;
		}

		for (List<InteData> l : allBatchSubList) {
			Logger.write("--_ size _--" + l.size());
		}

		return allBatchSubList;
	}

	public MultiBatchThreadCounter getThreadCounter() {
		return threadCounter;
	}

	public void setThreadCounter(MultiBatchThreadCounter threadCounter) {
		this.threadCounter = threadCounter;
	}

	public int getNumberOfRecordsToBeExecuteEachTime() {
		return numberOfRecordsToBeExecuteEachTime;
	}

	public void setNumberOfRecordsToBeExecuteEachTime(int numberOfRecordsToBeExecuteEachTime) {
		this.numberOfRecordsToBeExecuteEachTime = numberOfRecordsToBeExecuteEachTime;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public DataSource getSourceDataSource() {
		return sourceDataSource;
	}

	public void setSourceDataSource(DataSource sourceDataSource) {
		this.sourceDataSource = sourceDataSource;
	}

	public DataSource getDestinationDataSource() {
		return destinationDataSource;
	}

	public void setDestinationDataSource(DataSource destinationDataSource) {
		this.destinationDataSource = destinationDataSource;
	}
	
	

}
