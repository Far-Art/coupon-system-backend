package com.fa.CouponsMsProject.utils.generators;

import java.util.Random;

public class CouponDescriptionGenerator {
private static final Random randomNumber = new Random();
	
	public static String getDescription() {
		int randomNum = randomNumber.nextInt(10);
		switch (randomNum) {
		case 0:	
			return "Get two of this for price of four";
		case 1:	
			return "Realy interesting one";
		case 2:	
			return "Make dream come true";
		case 3:	
			return "My company was to lazy to write a description";
		case 4:	
			return "Read the title again to understand";
		case 5:	
			return "Artur is tired";
		case 6:	
			return "Shut up and take my MONEY";
		case 7:	
			return "pretty one";
		case 8:	
			return "very very very very very very long description";
		case 9:	
			return "That coupon was yesterday";
		default:
			return "to lame to implement this from a text file";
		}
	}
}
