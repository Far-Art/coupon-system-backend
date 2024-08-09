package com.fa.CouponsMsProject.utils.generators;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.beans.Company;
import com.fa.CouponsMsProject.beans.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class CompanyGenerator {

    private final CouponsGenerator couponsGenerator;

    private static final int COUPONS_PER_COMPANY = 3;

    private static Category companyProfile = null;

    /**
     * generate new company with ready coupons
     */
    public Company generate(){
        return generate(companyProfile);
    }

    public Company generate(Category profile) {
        // if profile null, make random profile
        if(profile == null){
            int len = Category.values().length;
            int rand = new Random().nextInt(len);
            companyProfile = Category.values()[rand];
        } else {
            companyProfile = profile;
        }

        // create company
        Company company = new Company();
        company.setActive(true);
        String name = CompanyNameGenerator.generateName(companyProfile);
        String email = EmailGenerator.generateEmail(name);
        company.setName(name + " " + email.split("@")[1].split("\\.")[0]);
        company.setEmail(email);
        company.setPassword(PasswordGenerator.getPassword());

        // add coupons to company by profile

        for (int i = 0; i < COUPONS_PER_COMPANY; i++) {
            Coupon coupon = couponsGenerator.getCoupon(companyProfile);
            coupon.setTitle(coupon.getTitle() + " " + (i + 1));
//            coupon.setImageUrl(imageGeneratorApi.getRandomImageByCategory(300,300, couponCategory));
            company.addCoupon(coupon);
        }
        companyProfile = null; // reset profile
        return company;
    }
}
