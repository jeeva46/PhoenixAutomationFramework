package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCsvFile {

	public static void main(String[] args) throws IOException, CsvException {
	
		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testdata/loginCredentials.csv");
		InputStreamReader reader = new InputStreamReader(resourceAsStream);
		CSVReader csvReader = new CSVReader(reader);
		
		List<String[]> list = csvReader.readAll();
		
		for(String[] listData:list) {
			for(String data: listData)
			System.out.println(data);
		}
		
	}

}
