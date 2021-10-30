package com.fa.CouponsMsProject.repositories;

import com.fa.CouponsMsProject.utils.CategoryToString;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryToString, Long> {
	
}