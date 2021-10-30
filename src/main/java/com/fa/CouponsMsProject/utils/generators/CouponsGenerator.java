package com.fa.CouponsMsProject.utils.generators;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.beans.Coupon;
import com.fa.CouponsMsProject.utils.ImageGeneratorApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class CouponsGenerator {

    @Autowired
    private ImageGeneratorApi imageGeneratorApi;

    public Coupon getCoupon(Category profile){
        return builder(profile);
    }

    private Coupon builder(Category profile){
        Coupon coupon = Coupon.builder()
                .amount(CouponAmountGenerator.getAmount())
                .category(profile)
                .price(CouponPriceGenerator.getPrice())
                .startDate((Date) CouponDateGenerator.getDate(1, true, CouponDateGenerator.DateType.SQL))
                .endDate((Date) CouponDateGenerator.getDate(10, true, CouponDateGenerator.DateType.SQL))
                .build();
        CouponTitleDescriptionGenerator.setTitleAndDesc(coupon, profile);
        return coupon;
    }
}
