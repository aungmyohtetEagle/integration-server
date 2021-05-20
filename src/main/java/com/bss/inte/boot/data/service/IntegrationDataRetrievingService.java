package com.bss.inte.boot.data.service;

import java.util.List;
import com.bss.inte.boot.domain.InteData;

public interface IntegrationDataRetrievingService {
	
	public List<InteData> retrieveDataWithProcessID(Long processId);

}
