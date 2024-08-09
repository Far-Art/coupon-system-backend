package com.fa.CouponsMsProject.jobs;

import com.fa.CouponsMsProject.beans.Coupon;
import com.fa.CouponsMsProject.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.util.Set;

import static com.fa.CouponsMsProject.config.ApplicationConfig.DAILY_INTERVAL;

//@Component

@Profile("prod")
@RequiredArgsConstructor
public class ExpiredCouponRemovalJob {

    private final CouponRepository couponRepository;
    private Set<Coupon> expiredCoupons;

    @Scheduled(initialDelay = 30000, fixedRate = DAILY_INTERVAL)
    public void runExpiredCouponsRemoval() {
        Date dateNow = new Date(new java.util.Date().getTime());
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