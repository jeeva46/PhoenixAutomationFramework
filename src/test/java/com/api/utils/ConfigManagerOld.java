package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {
	
	private static Properties prop = new Properties();
	
	
	static {                           //static block executes only once during class loading
		File file = new File(System.getProperty("user.dir")+"/src/test/resources/configuration/config.properties");
		FileReader reader;
		try {
			reader = new FileReader(file);
			prop.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//private constructor so that now one can create object
	private ConfigManagerOld(){
		
	}
	
	public static String getProperty(String key) throws IOException {

		
		return prop.getProperty(key);
		
	}

}
