package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataProviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtility {
	
	public static void LoqadCSV(String pathOfCSVFile) {
		
		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader reader = new InputStreamReader(resourceAsStream);
		CSVReader csvReader = new CSVReader(reader);
		
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();
		List<UserBean> list = csvToBean.parse();
		System.out.println(list.get(0).getUsername());
	
		
	}

}
