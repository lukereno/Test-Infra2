package com.luke.infra.platform.dataprovider;

import com.luke.infra.platform.dataprovider.impl.CsvDataProviderImpl;

public class DataProviderFactory {
	
	private DataProviderFactory() {
		
	}

	public static InfraDataProvider getDataProvider(DataProviderType dataProviderType) {
		
		switch (dataProviderType.getProviderType()) {
			case "CSV":
				return new CsvDataProviderImpl();
			default:
	            return null;
        }
	}
}
