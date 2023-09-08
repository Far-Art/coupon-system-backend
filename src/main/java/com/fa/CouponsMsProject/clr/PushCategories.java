package com.fa.CouponsMsProject.clr;

import com.fa.CouponsMsProject.repositories.CategoryRepository;
import com.fa.CouponsMsProject.utils.CategoryToString;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Profile({"dev"})
@RequiredArgsConstructor
public class PushCategories implements CommandLineRunner {

	private final CategoryRepository categoryRepository;

	@Override
	public void run(String... args) {
		categoryRepository.saveAll(CategoryToString.getAllCategories(false));
		System.out.println("Categories pushed to DB");
	}
}