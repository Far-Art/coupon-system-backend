package com.fa.CouponsMsProject.utils.generators;

import com.fa.CouponsMsProject.beans.Category;

import java.util.Random;

public class CategoryGenerator {
	private static final Random randomNumber = new Random();

	public static Category getCategory() {
		return Category.values()[randomNumber.nextInt(Category.values().length)];
	}
}
