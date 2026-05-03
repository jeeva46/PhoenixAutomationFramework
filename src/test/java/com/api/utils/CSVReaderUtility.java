package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtility {

	public static <T> Iterator<T> LoadCSV(String pathOfCSVFile, Class<T> bean) {

		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(pathOfCSVFile);
		InputStreamReader reader = new InputStreamReader(resourceAsStream);
		CSVReader csvReader = new CSVReader(reader);

		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true).build();
		
		List<T> list = csvToBean.parse();

		return list.iterator();

	}

}
