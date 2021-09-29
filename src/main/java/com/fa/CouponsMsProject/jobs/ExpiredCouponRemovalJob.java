package com.fa.CouponsMsProject.jobs;

import com.fa.CouponsMsProject.beans.Coupon;
import com.fa.CouponsMsProject.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Set;

import static com.fa.CouponsMsProject.config.ApplicationConfig.EXPIRED_COUPONS_REMOVAL_JOB_INTERVAL_MILLIS;

@Component
@RequiredArgsConstructor
public class ExpiredCouponRemovalJob {

	private Date dateNow;

	private Set<Coupon> expiredCoupons;

	private final CouponRepository couponRepository;

	@Scheduled(initialDelay = 30000, fixedRate = EXPIRED_COUPONS_REMOVAL_JOB_INTERVAL_MILLIS)
	public void runExpiredCouponsRemoval() {
		dateNow = new Date(new java.util.Date().getTime());
		fetchExpiredCoupons(dateNow);
		couponRepository.deleteInBatch(expiredCoupons);
	}

	private void fetchExpiredCoupons(Date date) {
		expiredCoupons = couponRepository.findAllByEndDateLessThan(date);
	}

	public Set<Coupon> getDeletedCoupons() {
		return expiredCoupons;
	}
}