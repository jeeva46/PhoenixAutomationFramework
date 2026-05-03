package com.dataProviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.pojo.CreateJob;
import com.api.utils.CSVReaderUtility;
import com.api.utils.CreateJobBeanMapper;
import com.dataProviders.api.bean.CreateJobBean;
import com.dataProviders.api.bean.UserBean;

public class DataProvidersUtil {
	
	
	@DataProvider(name = "Login",parallel = true)
	public static Iterator<UserBean> LoginAPIDataProvider() {
		
		return CSVReaderUtility.LoadCSV("testdata/loginCredentials.csv",UserBean.class);
		
	}
	
	
	@DataProvider(name="CreateJobDataProvider")
	public static Iterator<CreateJob> CreateJobDataProvider() {
		
		Iterator<CreateJobBean> createJobBean = CSVReaderUtility.LoadCSV("testdata/CreateJobData.csv", CreateJobBean.class);
		List<CreateJob> payload = new ArrayList();
		
		while(createJobBean.hasNext()) {
			CreateJobBean createjob = createJobBean.next();
			CreateJob mapper = CreateJobBeanMapper.mapper(createjob);
			payload.add(mapper);
		}
		
		return payload.iterator();
	}
	
	

}
