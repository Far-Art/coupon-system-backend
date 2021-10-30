package com.fa.CouponsMsProject.repositories;

import com.fa.CouponsMsProject.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

	Customer findByEmailAndPassword(String email, String password);

	boolean existsByIdAndEmail(long id, String email);
}