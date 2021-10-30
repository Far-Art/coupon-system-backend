package com.fa.CouponsMsProject.facades.impl;

import com.fa.CouponsMsProject.beans.*;
import com.fa.CouponsMsProject.exceptions.CustomException;
import com.fa.CouponsMsProject.repositories.CouponRepository;
import com.fa.CouponsMsProject.repositories.CustomerRepository;
import com.fa.CouponsMsProject.facades.ClientFacade;
import com.fa.CouponsMsProject.facades.CustomerFacade;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants.Exclude;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class CustomerFacadeImpl extends ClientFacade implements CustomerFacade {

	@Exclude
	private Customer customer;

	@Exclude
	private final CouponRepository couponRepository;

	@Exclude
	private final CustomerRepository customerRepository;

	@Override
	public Client getClient() {
		return customer;
	}

	@Override
	public Client getClient(long id) {
		customer = customerRepository.findById(id).get();
		return customer;
	}

	@Override
	public Client login(String email, String password){
		customer = customerRepository.findByEmailAndPassword(email, password);
		clientType = ClientType.CUSTOMER;
		return customer;
	}

	public int buyCoupons(List<Long> couponIds) throws CustomException {
		/* retrieve all coupons by list of ids */
		List<Coupon> coupons = couponRepository.findByIdIn(couponIds);

		/* validate coupons */
		couponValidation(coupons);

		/* update coupons amount */
		for (Coupon toBuy: coupons) {
			toBuy.setAmount(toBuy.getAmount() - 1);
			customer.addCoupon(toBuy);
			couponRepository.deleteAllRelations(customer.getId());
		}

		/* delete old relations */
		couponRepository.deleteAllRelations(customer.getId());

		/* Update customer in Database and make new relations */
		customerRepository.saveAndFlush(customer);

		return coupons.size(); // number of bought coupons
	}

	@Override
	public List<Coupon> getMyCoupons() {
		return couponRepository.findByCustomersOrderById(customer);
	}

	@Override
	public List<Coupon> getMyCoupons(Category ofCategory) throws CustomException {

		List<Coupon> coupons = couponRepository.findByCustomersAndCategoryOrderById(customer, ofCategory);

		if (coupons.isEmpty()) throw new CustomException("Coupons list is empty, try changing filters");
		return coupons;
	}

	@Override
	public List<Coupon> getMyCoupons(double priceLessThanEqual) throws CustomException {

		List<Coupon> coupons = couponRepository.findByCustomersAndPriceLessThanEqualOrderById(customer,
				priceLessThanEqual);
		if (coupons.isEmpty()) {
			throw new CustomException("Coupons list is empty, try changing filters");
		}
		return coupons;
	}

	@Override
	public Customer getMyInfo() {
		return customerRepository.findById(customer.getId()).get();
	}

	/**
	 * this method deleted all relations of purchased coupons of a customer
	 */
	public void dismissAllCoupons(){
		customer.getCoupons().clear();
		couponRepository.deleteAllRelations(customer.getId());
	}


	/**
	 * validate coupon
	 */
	private void couponValidation(long couponId) throws CustomException {
		/* Check if coupon exist */
		if (!couponRepository.existsById(couponId)) {
			throw new CustomException("Coupon with id(" + couponId + ") not found");
		}

		/* Init coupon from Database */
		Coupon toBuy = couponRepository.getOne(couponId);

		/* Check if you already possess this coupon */
		if (customer.getCoupons().contains(toBuy)) {
			throw new CustomException("You already own coupon with id(" + couponId + ")");
		}

		/* Check if coupon is sold out */
		if (toBuy.getAmount() == 0) {
			throw new CustomException("Coupon with id(" + couponId + ") is sold out");
		}

		/* Check if coupon expired */
		if (toBuy.getEndDate().toLocalDate().isBefore(LocalDate.now())){
			throw new CustomException("Coupon with id(" + toBuy.getId() + ") expired at " + toBuy.getEndDate());
		}
	}

	/**
	 * validate coupon
	 */
	private void couponValidation(List<Coupon> coupons) throws CustomException {

		/* null / empty check */
		if(coupons == null || coupons.isEmpty()){
			throw new CustomException("Coupons not found");
		}

		for (Coupon toBuy: coupons) {
			/* Check if you already possess this coupon */
			if (customer.getCoupons().contains(toBuy)) {
				throw new CustomException("You already own coupon (" + toBuy.getTitle() + ")");
			}

			/* Check if coupon is sold out */
			if (toBuy.getAmount() == 0) {
				throw new CustomException("Coupon (" + toBuy.getTitle() + ") is sold out");
			}

			/* Check if coupon is coming soon */
			if (toBuy.getStartDate().toLocalDate().isAfter(LocalDate.now())) {
				throw new CustomException("Coupons (" + toBuy.getTitle() + ") sale not started yet");
			}

			/* Check if coupon expired */
			if (toBuy.getEndDate().toLocalDate().isBefore(LocalDate.now())) {
				throw new CustomException("Coupon (" + toBuy.getTitle() + ") expired at " + toBuy.getEndDate());
			}
		}
	}
}