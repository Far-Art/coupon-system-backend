package com.fa.CouponsMsProject.utils.generators;

import java.util.Random;

public class ClientNameGenerator {
	private static final Random randomNumber = new Random();
	private static String name;

	public static String getSameName() {
		if (name == null) {
			return getName();
		}
		return name;
	}

	public static String getName() {
		int randomNum = randomNumber.nextInt(16);
		switch (randomNum) {
		case 0:
			name = "Alessandra";
			return name;
		case 1:
			name = "Cliff";
			return name;
		case 2:
			name = "Owens";
			return name;
		case 3:
			name = "Liam";
			return name;
		case 4:
			name = "Donalda";
			return name;
		case 5:
			name = "Patterson";
			return name;
		case 6:
			name = "Sullivan";
			return name;
		case 7:
			name = "Kobi";
			return name;
		case 8:
			name = "Stevie";
			return name;
		case 9:
			name = "Pauline";
			return name;
		case 10:
			name = "Spencer";
			return name;
		case 11:
			name = "Romero";
			return name;
		case 12:
			name = "Diaz";
			return name;
		case 13:
			name = "Barnes";
			return name;
		case 14:
			name = "Powell";
			return name;
		case 15:
			name = "Nicoletta";
			return name;
		default:
			name = "to lame to implement this from a text file";
			return name;
		}
	}
}
