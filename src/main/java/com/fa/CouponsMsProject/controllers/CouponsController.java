package com.fa.CouponsMsProject.controllers;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.dto.CouponDto;
import com.fa.CouponsMsProject.mappers.CategoriesMapper;
import com.fa.CouponsMsProject.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.fa.CouponsMsProject.security.constants.SecurityConstants.ALLOWED_HEADERS;
import static com.fa.CouponsMsProject.security.constants.SecurityConstants.ORIGINS;

@RestController
@RequestMapping(value = "/coupons")
@RequiredArgsConstructor
@CrossOrigin(origins = ORIGINS, allowedHeaders = ALLOWED_HEADERS)
public class CouponsController extends ClientController {

    private final CouponRepository couponRepository;

    private final CategoriesMapper categoriesMapper;

    @GetMapping
    public ResponseEntity<List<CouponDto>> getAllCoupons() {
        List<CouponDto> coupons = couponModelMapper.entityToDto(couponRepository.findAll());
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponDto> getSingleCoupon(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(couponModelMapper.entityToDto(couponRepository.findById(id).get()), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCouponsCategories() {
        return new ResponseEntity<>(categoriesMapper.categoryToString(Arrays.asList(Category.values())), HttpStatus.OK);
    }
}