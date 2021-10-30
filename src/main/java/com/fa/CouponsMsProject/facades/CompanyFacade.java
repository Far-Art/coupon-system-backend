package com.fa.CouponsMsProject.facades;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.beans.Company;
import com.fa.CouponsMsProject.beans.Coupon;
import com.fa.CouponsMsProject.exceptions.CustomException;

import java.util.List;

public interface CompanyFacade {

	void addCoupon(Coupon coupon) throws CustomException;

	void updateCoupon(Coupon coupon) throws CustomException;

	void deleteCoupon(long couponId) throws CustomException;

	List<Coupon> getAllCoupons() throws CustomException;

	List<Coupon> getAllCoupons(Category category) throws CustomException;

	List<Coupon> getAllCoupons(double priceEqualOrBelow) throws CustomException;

	Company getMyInfo();

}
