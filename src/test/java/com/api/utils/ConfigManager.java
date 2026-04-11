package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.mozilla.javascript.ast.SwitchCase;

public class ConfigManager {
	
	private static Properties prop = new Properties();
	private static String path="configuration/config.properties";
	
	//static block executes only once during class loading
	static {      
		
		
		String env = System.getProperty("env", "qa");
		env=env.toLowerCase().trim();
		switch (env){
		
		case "dev":
			path="configuration/config.dev.properties";
			break;
			
		case "qa":
			path="configuration/config.qa.properties";
			break;
			
		case "uat":
			path="configuration/config.uat.properties";
			break;
			
		default: path="configuration/config.properties";
			
		}
		
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
		if(input==null) {
			throw new RuntimeException("input is null");
		}
		
		
		try {
			
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//private constructor so that now one can create object
	private ConfigManager(){
		
	}
	
	public static String getProperty(String key) throws IOException {

		
		return prop.getProperty(key);
		
	}

}
