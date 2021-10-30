package com.fa.CouponsMsProject.utils.generators;

import java.util.Random;

public class PasswordGenerator {

	private static final int MIN_PASSWORD_LENGTH = 8;
	private static final int MAX_PASSWORD_LENGTH = 16;
	private static final Random randomNumber = new Random();

	public static String getPassword() {
		// init String for password
		StringBuilder password = new StringBuilder();

		// init password length
		int randomPasswordLength = randomNumber.nextInt(MAX_PASSWORD_LENGTH - MIN_PASSWORD_LENGTH - 1)
				+ MIN_PASSWORD_LENGTH;

		// place random capital letter
		int capChar = randomNumber.nextInt(26) + 65; // ASCII random capital letter
		password.append(Character.toString(capChar));

		// place random digit
		password.append(randomNumber.nextInt(10));

		// place other characters
		for (int i = 0; i < randomPasswordLength; i++) {
			int randChar = randomNumber.nextInt(75) + 48;

			// check that randChar falls under digit or character criterion
			while (!((randChar >= 48 && randChar <= 57) || (randChar >= 64 && randChar <= 90)
					|| (randChar >= 97 && randChar <= 122))) {
				randChar = randomNumber.nextInt(75) + 48;
			}

			password.append(Character.toString(randChar));
		}
		return password.toString();
	}
}
