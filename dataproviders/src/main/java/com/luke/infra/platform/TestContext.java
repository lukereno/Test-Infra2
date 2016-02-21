package com.luke.infra.platform;

import java.util.Iterator;
import java.util.List;

import com.luke.infra.platform.dataprovider.DataProviderException;
import com.luke.infra.platform.dataprovider.InfraDataProvider;
import com.luke.infra.platform.dataprovider.impl.CsvDataProviderImpl;

/**
 * Defines the data to be shared with the DataProvider Implementations
 */
public class TestContext {
	
	private String dataProviderFilePath;
	private String dataReaderClasspath;
	private String dataRange;
	//private String dataType;
	
	public String getDataProviderFilePath() {
		return dataProviderFilePath;
	}
	public void setDataProviderFilePath(String dataProviderFilePath) {
		this.dataProviderFilePath = dataProviderFilePath;
	}
	public String getDataReaderClasspath() {
		return dataReaderClasspath;
	}
	public void setDataReaderClasspath(String dataReaderClasspath) {
		this.dataReaderClasspath = dataReaderClasspath;
	}
	public String getDataRange() {
		return dataRange;
	}
	public void setDataRange(String dataRange) {
		this.dataRange = dataRange;
	}
	
	public Object getDataReaderInstance() {
		
		Object readerObj;
		try {
			readerObj = Class.forName(dataReaderClasspath).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
			throw new DataProviderException(e);
		}
		
		return readerObj;
	}
	
	public Iterator<Object[]> getDataReader() {
		//Use factory and get file type from dataProviderFilePath
		InfraDataProvider dr = new CsvDataProviderImpl(); 
		List<Object[]> dro = dr.getRows(this);
		return dro.iterator();
	}
}

