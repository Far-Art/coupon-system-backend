package com.fa.CouponsMsProject.utils;

import com.fa.CouponsMsProject.beans.Category;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class CategoryToString {

	private static List<CategoryToString> CATEGORIES = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private int id;

	@Transient
	private final Category category;

	@Column(name = "category")
	private final String categoryLabel;

	// CTOR
	public CategoryToString(Category category) {
		this.category = category;
		this.categoryLabel = initLabel(category);
	}

	public static List<CategoryToString> getAllCategories(boolean update) {
		if (CATEGORIES.isEmpty() || update) {
			synchronized (CATEGORIES) {
				if (CATEGORIES.isEmpty() || update) {
					initList();
				}
			}
		}
		return CATEGORIES;
	}

	// init the String representation of a category
	private static String initLabel(Category category) {
		String lowerCase;
		String subString;
		subString = category.toString().substring(0, 1);
		lowerCase = (category.toString().substring(1)).toLowerCase();
		return (subString + lowerCase);
	}

	// init category list
	private static void initList() {
		for (Category category : Category.values()) {
			CATEGORIES.add(new CategoryToString(category));
		}
	}

}
