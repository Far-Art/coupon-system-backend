package com.fa.CouponsMsProject.repositories;

import com.fa.CouponsMsProject.beans.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findByEmailAndPassword(String email, String password);

	boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

	boolean existsByIdAndEmail(long id, String email);

}