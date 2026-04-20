package com.api.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

	private DateTimeUtil() {
		
	}
	
	public static String getDateTime(int day) {
		
		String time = Instant.now().minus(day,ChronoUnit.DAYS).toString();
		
		return time;
	}
	
}
