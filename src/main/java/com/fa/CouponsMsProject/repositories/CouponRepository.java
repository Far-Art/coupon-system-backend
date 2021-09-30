package com.fa.CouponsMsProject.repositories;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.beans.Company;
import com.fa.CouponsMsProject.beans.Coupon;
import com.fa.CouponsMsProject.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	List<Coupon> findByCustomersOrderById(Customer customer);

	List<Coupon> findByCustomersAndCategoryOrderById(Customer customer, Category category);

	List<Coupon> findByCustomersAndPriceLessThanEqualOrderById(Customer customer, double price);

	boolean existsByCompanyAndTitle(Company company, String title);

	boolean existsByCompanyAndId(Company company, long couponId);

	List<Coupon> findByCompanyOrderById(Company company);

	List<Coupon> findByCompanyAndCategoryOrderById(Company company, Category category);

	List<Coupon> findByCompanyAndPriceLessThanEqualOrderById(Company company, double price);

	Set<Coupon> findAllByEndDateLessThan(Date date);

	List<Coupon> findByIdIn(List<Long> couponIds);
	
	@Modifying
	@Transactional
//	@Query(value = "DELETE FROM couponasart_ms.customers_coupons WHERE `customer_id`=?;", nativeQuery = true)
	void deleteAllRelations(@Param("customer_id") long customerId);

}