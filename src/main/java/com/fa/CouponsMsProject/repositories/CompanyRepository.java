package com.fa.CouponsMsProject.repositories;

import com.fa.CouponsMsProject.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	boolean existsByName(String name);

	boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

	Company findByEmailAndPassword(String email, String password);
	
	boolean existsByIdAndEmail(long id, String email);
}