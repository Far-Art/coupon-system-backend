package com.fa.CouponsMsProject.facades;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.beans.Coupon;
import com.fa.CouponsMsProject.beans.Customer;
import com.fa.CouponsMsProject.exceptions.CustomException;

import java.util.List;

public interface CustomerFacade {

	int buyCoupons(List<Long> coupons) throws CustomException;

	List<Coupon> getMyCoupons();

	List<Coupon> getMyCoupons(Category ofCategory) throws CustomException;

	List<Coupon> getMyCoupons(double priceLessThanEqual) throws CustomException;

	Customer getMyInfo();

    void dismissAllCoupons();
}