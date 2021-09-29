package com.fa.CouponsMsProject.utils.generators;

import java.util.Random;

public class CouponAmountGenerator {
	private static final Random randomNumber = new Random();
	private static final int MAX_COUNT_LENGTH = 4;
	
	public static int getAmount() {
		return randomNumber.nextInt(MAX_COUNT_LENGTH);
	}
}
