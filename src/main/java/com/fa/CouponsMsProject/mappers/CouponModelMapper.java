package com.fa.CouponsMsProject.mappers;

import com.fa.CouponsMsProject.beans.Coupon;
import com.fa.CouponsMsProject.dto.CouponDto;
import com.fa.CouponsMsProject.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CouponModelMapper {

    private final CategoriesMapper categoriesMapper;

    public CouponDto entityToDto(Coupon coupon){
        return CouponDto.builder()
                .id(coupon.getId())
                .companyEntity(coupon.getCompany())
                .companyName(coupon.getCompany().getName())
                .companyEmail(coupon.getCompany().getEmail())
                .category(categoriesMapper.categoryToString(coupon.getCategory()))
                .title(coupon.getTitle())
                .description(coupon.getDescription())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .amount(coupon.getAmount())
                .price(coupon.getPrice())
                .imageUrl(coupon.getImageUrl())
                .build();
    }

    @SneakyThrows
    public Coupon dtoToEntity(CouponDto coupon){
        if(coupon.getAmount() % 1 != 0){
            throw new CustomException("Coupon amount cannot have fractions");
        }
        return Coupon.builder()
                .id(coupon.getId())
                .company(coupon.getCompanyEntity())
                .category(categoriesMapper.stringToCategory(coupon.getCategory()))
                .title(coupon.getTitle())
                .description(coupon.getDescription())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .amount((int)coupon.getAmount())
                .price(coupon.getPrice())
                .imageUrl(coupon.getImageUrl())
                .build();
    }

    public List<CouponDto> entityToDto(List<Coupon> coupons){
        return coupons.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Coupon> dtoToEntity(List<CouponDto> coupons){
        return coupons.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}