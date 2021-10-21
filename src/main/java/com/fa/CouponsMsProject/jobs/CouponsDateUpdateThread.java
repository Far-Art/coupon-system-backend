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

    private List<Coupon> coupons;

    private final CouponRepository couponRepository;

    @Scheduled(fixedRate = DAILY_INTERVAL * 2)
    public void runDateUpdate(){
        coupons = couponRepository.findAll();
        for (int i = 0; i < coupons.size(); i++) {
            updateDates(coupons.get(i));
        }
        couponRepository.saveAll(coupons);
    }

    private void updateDates(Coupon coupon){
        coupon.setStartDate((Date) CouponDateGenerator.getDate(2, true, CouponDateGenerator.DateType.SQL));
        coupon.setEndDate((Date) CouponDateGenerator.getDate(10, true, CouponDateGenerator.DateType.SQL));
    }
}
