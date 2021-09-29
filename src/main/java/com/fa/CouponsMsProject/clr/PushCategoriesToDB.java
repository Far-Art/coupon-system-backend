package com.fa.CouponsMsProject.clr;

import com.fa.CouponsMsProject.repositories.CategoryRepository;
import com.fa.CouponsMsProject.utils.CategoryToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class PushCategoriesToDB implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) {
		categoryRepository.saveAll(CategoryToString.getAllCategories(false));
		System.out.println("Categories Pushed to DB");
	}
}