package com.fa.CouponsMsProject.facades.impl;

import com.fa.CouponsMsProject.beans.*;
import com.fa.CouponsMsProject.exceptions.CustomException;
import com.fa.CouponsMsProject.repositories.CompanyRepository;
import com.fa.CouponsMsProject.repositories.CouponRepository;
import com.fa.CouponsMsProject.facades.ClientFacade;
import com.fa.CouponsMsProject.facades.CompanyFacade;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants.Exclude;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class CompanyFacadeImpl extends ClientFacade implements CompanyFacade {

	@Exclude
	private Company company;

	@Exclude
	private final CouponRepository couponRepository;

	@Exclude
	private final CompanyRepository companyRepository;

	@Override
	public Client getClient() {
		return company;
	}

	@Override
	public Client getClient(long id) {
		company = companyRepository.findById(id).get();
		return company;
	}

	@Override
	public Client login(String email, String password) {
		company = companyRepository.findByEmailAndPassword(email, password);
		clientType = ClientType.COMPANY;
		return company;
	}

	@Override
	public void addCoupon(Coupon coupon) throws CustomException {

		/* check if such coupon with title exist */
		if (couponRepository.existsByCompanyAndTitle(company, coupon.getTitle())
				|| couponRepository.existsById(coupon.getId())) {
			throw new CustomException("You already possess such coupon with title(" + coupon.getTitle() + ")");
		}

		couponValidation(coupon);

		/* set company in coupon relation */
		coupon.setCompany(company);

		couponRepository.save(coupon);
	}

	@Override
	public void updateCoupon(Coupon coupon) throws CustomException {

		/* check if such coupon exist in database */
		if (!couponRepository.existsByCompanyAndId(company, coupon.getId())) {
			throw new CustomException("You don't possess coupon with id(" + coupon.getId() + ")");
		}

		couponValidation(coupon);

		/* check if title was changed */
		if (!couponRepository.getOne(coupon.getId()).getTitle().equalsIgnoreCase(coupon.getTitle())) {
			/* check if title is not taken */
			if (couponRepository.existsByCompanyAndTitle(company, coupon.getTitle())) {
				throw new CustomException("You already possess coupon with title(" + coupon.getTitle() + ")");
			}
		}

		/* reset the company relation if it was changed */
		coupon.setCompany(company);

		couponRepository.save(coupon);
	}

	@Override
	public void deleteCoupon(long couponId) throws CustomException {

		/* check if such coupon exist in database */
		if (!couponRepository.existsByCompanyAndId(company, couponId)) {
			throw new CustomException("You does not possess coupon with id(" + couponId + ")");
		}
		couponRepository.deleteById(couponId);
	}

	@Override
	public List<Coupon> getAllCoupons() throws CustomException {

		List<Coupon> coupons = couponRepository.findByCompanyOrderById(company);

		/* check if list is not empty */
		if (coupons.isEmpty()) {
			throw new CustomException("No coupons found, try adding some coupons");
		}
		return coupons;
	}

	@Override
	public List<Coupon> getAllCoupons(Category category) throws CustomException {

		List<Coupon> coupons = couponRepository.findByCompanyAndCategoryOrderById(company, category);

		/* check if list is not empty */
		if (coupons.isEmpty()) {
			throw new CustomException("No coupons found, try changing filters");
		}
		return coupons;
	}

	@Override
	public List<Coupon> getAllCoupons(double priceEqualOrBelow) throws CustomException {

		List<Coupon> coupons = couponRepository.findByCompanyAndPriceLessThanEqualOrderById(company, priceEqualOrBelow);

		/* check if list is not empty */
		if (coupons.isEmpty()) {
			throw new CustomException("No coupons found, try changing filters");
		}
		return coupons;
	}

	@Override
	public Company getMyInfo() {
		return companyRepository.findById(company.getId()).get();
	}

	public Map<Long, Long> getPurchasedCoupons() throws CustomException {
		Map<Long, Long> purchasedCoupons = new HashMap<>();

		/* fetch all my coupons */
		List<Coupon> myCoupons = getAllCoupons();

		/* init the map */
		for (Coupon coupon : myCoupons) {
			purchasedCoupons.put(coupon.getId(), (long) coupon.getCustomers().size());
		}
		return purchasedCoupons;
	}

	private void couponValidation(Coupon coupon) throws CustomException {
		LocalDate localDate = LocalDate.now();
		if(coupon.getStartDate().toLocalDate().isBefore(localDate)){
			throw new CustomException("Cannot insert new coupon retroactively");
		} else if (coupon.getStartDate().toLocalDate().isAfter(coupon.getEndDate().toLocalDate())){
			throw new CustomException("Start date cannot exceed end date");
		} else if (coupon.getTitle().isEmpty()){
			throw new CustomException("Title cannot be empty");
		} else if (coupon.getTitle().length() > 60){
			throw new CustomException("Title cannot exceed 60 characters");
		} else if (coupon.getDescription().length() > 200){
			throw new CustomException("Description cannot exceed 200 characters");
		} else if (coupon.getAmount() % 1 != 0 || coupon.getAmount() < 0){
			throw new CustomException("Amount must be a whole positive number");
		} else if (coupon.getAmount() > Integer.MAX_VALUE){
			throw new CustomException("Amount cannot exceed " + Integer.MAX_VALUE);
		} else if (coupon.getPrice() < 0){
			throw new CustomException("Price must start from 0");
		} else if (coupon.getPrice() > 9999){
			throw new CustomException("Price cannot exceed 9999");
		} else if (coupon.getImage().length() > 500 ){
			throw new CustomException("Image Url cannot exceed 500 characters");
		}
	}
}