package com.fa.CouponsMsProject.utils.generators;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CouponDateGenerator {

	public static Date getDate(int days, boolean isRandom, DateType type) {
		Calendar time = Calendar.getInstance();
		if (isRandom) {
			Random randomDays = new Random();
			days += randomDays.nextInt(3) - 2;
		}
		time.add(Calendar.DAY_OF_MONTH, days);

		if (type == DateType.JAVA) {
			return time.getTime();
		} else {
			return new java.sql.Date(time.getTimeInMillis());
		}
	}

	public enum DateType {
		SQL, JAVA
	}
}
