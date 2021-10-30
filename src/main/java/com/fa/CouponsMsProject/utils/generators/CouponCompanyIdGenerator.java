package com.fa.CouponsMsProject.utils.generators;

import java.util.Random;

public class CouponCompanyIdGenerator {
	private static final Random randomNumber = new Random();
	
	public static int getCompanyId() {
		return randomNumber.nextInt(50);
	}
}
