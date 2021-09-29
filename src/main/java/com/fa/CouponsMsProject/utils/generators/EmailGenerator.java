package com.fa.CouponsMsProject.utils.generators;

import java.util.Random;

public class EmailGenerator {
	private static final Random randomNumber = new Random();

	/**
	 * generate email based on provided client name
	 */
	public static String generateEmail(String name) {
		return name.replaceAll(" ", "").toLowerCase() + "@" + randomPrefix() + randomDomain();
	}

	private static String randomPrefix() {
		switch (randomNumber.nextInt(7)) {
		case 0:
			return "mail";
		case 1:
			return "walla";
		case 2:
			return "commercials";
		case 3:
			return "coupons";
		case 4:
			return "matrix";
		case 5:
			return "jb";
		case 6:
			return "discounts";
		}
		return "EMPTY VIEW";
	}

	private static String randomDomain() {
		switch (randomNumber.nextInt(6)) {
		case 0:
			return ".com";
		case 1:
			return ".ru";
		case 2:
			return ".isr";
		case 3:
			return ".co.il";
		case 4:
			return ".now";
		case 5:
			return ".net";
		}
		return "EMPTY VIEW";
	}
}
