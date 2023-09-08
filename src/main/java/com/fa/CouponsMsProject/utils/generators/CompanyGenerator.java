package com.fa.CouponsMsProject.utils.generators;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.beans.Company;
import com.fa.CouponsMsProject.beans.Coupon;
import com.fa.CouponsMsProject.utils.ImageGeneratorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class CompanyGenerator {

    private final CouponsGenerator couponsGenerator;

    private final ImageGeneratorApi imageGeneratorApi;

    private static final int COUPONS_PER_COMPANY = 6;

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
        Random random = new Random();
        String couponCategory = "";
        switch (companyProfile){
            case GROCERY:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "grocery,colorful";
                        break;
                    case 1:
                        couponCategory = "grocery";
                        break;
                    case 2:
                        couponCategory = "market,super";
                        break;
                }
                break;
            case HOTELS:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "hotel,exotic";
                        break;
                    case 1:
                        couponCategory = "hotels,beach";
                        break;
                    case 2:
                        couponCategory = "hotel,ocean";
                        break;
                }
                break;
            case BABY:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "baby,cute";
                        break;
                    case 1:
                        couponCategory = "toys,fun";
                        break;
                    case 2:
                        couponCategory = "toys";
                        break;
                }
                break;
            case RESTAURANT:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "restaurant";
                        break;
                    case 1:
                        couponCategory = "tasty";
                        break;
                    case 2:
                        couponCategory = "kitchen,high";
                        break;
                }
                break;
            case PERSONAL_CARE:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "spa";
                        break;
                    case 1:
                        couponCategory = "relax";
                        break;
                    case 2:
                        couponCategory = "enjoy";
                        break;
                }
                break;
            case HOUSEHOLD:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "household";
                        break;
                    case 1:
                        couponCategory = "house";
                        break;
                    case 2:
                        couponCategory = "fix";
                        break;
                }
                break;
            case ELECTRONICS:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "electronics";
                        break;
                    case 1:
                        couponCategory = "leds";
                        break;
                    case 2:
                        couponCategory = "microchip";
                        break;
                }
                break;
            case TOOLS:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "tools";
                        break;
                    case 1:
                        couponCategory = "powertools";
                        break;
                    case 2:
                        couponCategory = "handtools";
                        break;
                }
                break;
            case VACATION:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "vacation";
                        break;
                    case 1:
                        couponCategory = "beach";
                        break;
                    case 2:
                        couponCategory = "pool";
                        break;
                }
                break;
            case TRAVEL:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "travel";
                        break;
                    case 1:
                        couponCategory = "hiking";
                        break;
                    case 2:
                        couponCategory = "climb";
                        break;
                }
                break;
            case AUTOMOTIVE:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "retro,car";
                        break;
                    case 1:
                        couponCategory = "garage,car";
                        break;
                    case 2:
                        couponCategory = "car";
                        break;
                }
                break;
            case FASHION:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "fashion";
                        break;
                    case 1:
                        couponCategory = "vogue";
                        break;
                    case 2:
                        couponCategory = "clothes";
                        break;
                }
                break;
            case SOFTWARE:
                switch (random.nextInt(3)){
                    case 0:
                        couponCategory = "software";
                        break;
                    case 1:
                        couponCategory = "computer";
                        break;
                    case 2:
                        couponCategory = "cyber";
                        break;
                }
                break;
        }

        for (int i = 0; i < COUPONS_PER_COMPANY; i++) {
            Coupon coupon = couponsGenerator.getCoupon(companyProfile);
            coupon.setTitle(coupon.getTitle() + " " + (i + 1));
            coupon.setImageUrl(imageGeneratorApi.getRandomImageByCategory(300,300, couponCategory));
            company.addCoupon(coupon);
        }
        companyProfile = null; // reset profile
        return company;
    }
}
