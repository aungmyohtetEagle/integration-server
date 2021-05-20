package com.bss.inte.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:META-INF/spring/jdbc.properties")
public class PropertyProvider {

	@Value("${max_num_of_batch_threads}")
	private Integer maxNumberOfBatchThreads;

	@Value("${num_records_to_prepared_batch_stmt}")
	private Integer numberOfRecordsToPreparedBatchStatement;

	@Value("${max_num_of_records_assigned_to_thread}")
	private Long maxNumberOfRecordsAssignedToThread;

	public Integer getMaxNumberOfBatchThreads() {
		return maxNumberOfBatchThreads;
	}

	public Integer getNumberOfRecordsToPreparedBatchStatement() {
		return numberOfRecordsToPreparedBatchStatement;
	}

	public Long getMaxNumberOfRecordsAssignedToThread() {
		return maxNumberOfRecordsAssignedToThread;
	}

}
