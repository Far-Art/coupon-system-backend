package com.fa.CouponsMsProject.utils.generators;

import java.util.Random;

public class CouponPriceGenerator {
	
	private static final Random randomNumber = new Random();

	public static double getPrice() {
		return Math.floor(randomNumber.nextInt(500)) + randomNumber.nextInt(100) / 100.0;
	}
}
