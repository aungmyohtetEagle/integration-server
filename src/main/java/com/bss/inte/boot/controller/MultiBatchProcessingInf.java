package com.bss.inte.boot.controller;

import java.util.List;

import javax.sql.DataSource;

import com.bss.inte.boot.domain.InteData;

public interface MultiBatchProcessingInf extends Runnable {
	public void init(MultiBatchController batchController, List<InteData> computedBatchList);

}
