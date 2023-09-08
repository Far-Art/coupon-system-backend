package com.fa.CouponsMsProject.jobs;

import com.fa.CouponsMsProject.beans.Coupon;
import com.fa.CouponsMsProject.repositories.CouponRepository;
import com.fa.CouponsMsProject.utils.generators.CouponDateGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.util.List;
import static com.fa.CouponsMsProject.config.ApplicationConfig.DAILY_INTERVAL;

@Component
@RequiredArgsConstructor
public class CouponsDateUpdateThread {

    private final CouponRepository couponRepository;

    @Scheduled(fixedRate = DAILY_INTERVAL * 2)
    public void runDateUpdate(){
        List<Coupon> coupons = couponRepository.findAll();
        for (Coupon coupon : coupons) {
            updateDates(coupon);
        }
        couponRepository.saveAll(coupons);
    }

    private void updateDates(Coupon coupon){
        coupon.setStartDate((Date) CouponDateGenerator.getDate(2, true, CouponDateGenerator.DateType.SQL));
        coupon.setEndDate((Date) CouponDateGenerator.getDate(10, true, CouponDateGenerator.DateType.SQL));
    }
}
