package com.luke.infra.platform.dataprovider.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.luke.infra.platform.TestContext;
import com.luke.infra.platform.dataprovider.DataProviderException;
import com.luke.infra.platform.dataprovider.InfraDataProvider;

/**
 * CSV Data Reader Implementation
 */
public class CsvDataProviderImpl implements InfraDataProvider {

	private final String CSV_SPLIT_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";


	/* 
	 * This function will return read the specified csv file and return the rows as defined in the TestContext
	 * 
	 * @param ctx
	 * 			- The TestContext instance containing the csv path, DataReader class reference & range of rows to read
	 * @return - Array of Object's that can be cast to the users DataReader
	 */
	@Override
	public List<Object[]> getRows(TestContext ctx) {
		BufferedReader br = null;
		String line = "";

		List<Object[]> dataObjects = new ArrayList<>();

		try {

			br = new BufferedReader(new FileReader(ctx.getDataProviderFilePath()));
			Object reaaderObj = ctx.getDataReaderInstance();

			String headerLine = br.readLine();
			Map<String, Integer> headerMap = getHeaderMap(headerLine);

			int rowNumber = 0;
			List<Integer> rowsToRead = getRowsToReadFromString(ctx.getDataRange());
			while ((line = br.readLine()) != null) {
				rowNumber++;
				
				//TODO: refactor to method
				for (int rowToRead : rowsToRead) {
					if (rowToRead == rowNumber) {
						// use comma as separator
						String[] dataRow = line.split(CSV_SPLIT_REGEX);
						Object dataObject = mapLineToDataReaderObject(reaaderObj, headerMap,
								dataRow);
						dataObjects.add(new Object[] { dataObject });
					}
				}


			}

		} catch (IOException | IllegalAccessException e) {
			e.printStackTrace();
			throw new DataProviderException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return dataObjects;
	}

	private Map<String, Integer> getHeaderMap(String headerLine) {

		Map<String, Integer> headerMap = new HashMap<>();

		String[] headers = headerLine.split(CSV_SPLIT_REGEX);
		for (int i = 0; i < headers.length; i++) {
			headerMap.put(headers[i], i);
		}

		return headerMap;
	}

	private Object mapLineToDataReaderObject(Object dro,
			Map<String, Integer> headerMap, String[] rowData)
			throws IllegalAccessException {
		Class<?> cls = null;
		try {
			cls = Class.forName(dro.getClass().getName());

		} catch (ClassNotFoundException e) {
			// do something
		}

		Object objectToReturn = createObjectToUse(dro);

		List<Field> fields = new ArrayList<>();

		fields = getAllFields(fields, cls);

		for (Field eachField : fields) {
			Class<?> eachFieldType = eachField.getType();

			if (eachFieldType.isInterface()) {
				// do something
			}

			eachField.setAccessible(true);

			String fieldName = eachField.getName();
			int fieldIndex = headerMap.get(fieldName);
			
			//if last value in csv data row is empty
			String colValue = rowData.length<=fieldIndex ? "" : rowData[fieldIndex];

			eachField.set(objectToReturn, colValue);
		}

		return objectToReturn;
	}

	private List<Field> getAllFields(List<Field> fields, Class<?> type) {

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		return fields;
	}

	private Object createObjectToUse(Object userObject)
			throws IllegalAccessException {

		Object returnObj = null;

		try {
			// Create a new instance of the data so we can
			// store it here before return everything to the users.
			returnObj = userObject.getClass().newInstance();
		} catch (InstantiationException e1) {
			String msg = String
					.format("Unable to instantiate an object of class %s bcoz it doesn't have a default constructor. ",
							userObject.getClass().getCanonicalName());
			 throw new DataProviderException(msg, e1);
			// dosomething;
		}

		return returnObj;
	}
	
	private List<Integer> getRowsToReadFromString(String dataRows) {
		List<Integer> rowsToRead = new ArrayList<>();
		
		String[] tokens = dataRows.split(CSV_SPLIT_REGEX);
		
		for (String token : tokens) {
			if (token.contains("-")) {
				int lowerBound = Integer.valueOf(token.split("-")[0]); 
				int upperBound = Integer.valueOf(token.split("-")[1]); 
				
				while (lowerBound <= upperBound) {
					rowsToRead.add(lowerBound);
					lowerBound++;
				}
			}
			else {
				int idx = Integer.valueOf(token);
				rowsToRead.add(idx);
			}
		}
		
		return rowsToRead;
	}
}
