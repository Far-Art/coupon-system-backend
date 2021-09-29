package com.fa.CouponsMsProject.controllers;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.beans.ClientType;
import com.fa.CouponsMsProject.beans.Company;
import com.fa.CouponsMsProject.dto.CouponDto;
import com.fa.CouponsMsProject.exceptions.SecurityException;
import com.fa.CouponsMsProject.facades.AdminFacade;
import com.fa.CouponsMsProject.facades.impl.CompanyFacadeImpl;
import com.fa.CouponsMsProject.exceptions.CustomException;
import com.fa.CouponsMsProject.facades.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import static com.fa.CouponsMsProject.security.constants.SecurityConstants.ALLOWED_HEADERS;
import static com.fa.CouponsMsProject.security.constants.SecurityConstants.ORIGINS;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@CrossOrigin(origins = ORIGINS, allowedHeaders = ALLOWED_HEADERS)
public class CompanyController extends ClientController{

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType) throws SecurityException, CustomException {
        return new ResponseEntity<>(((AdminFacade) accessManager.getSession(token, clientType, ClientType.ADMIN)).getAllCompanies(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestBody Company company) throws CustomException, SecurityException {
        ((AdminFacade) accessManager.getSession(token, clientType, ClientType.ADMIN)).addCompany(company);
        return new ResponseEntity<>("Company " + company.getEmail() + " added", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateCompany(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestBody Company company) throws CustomException, SecurityException {
        ((AdminFacade) accessManager.getSession(token, clientType, ClientType.ADMIN)).updateCompany(company);
        return new ResponseEntity<>("Company " + company.getEmail() + " updated", HttpStatus.OK);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompany(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @PathVariable long companyId) throws CustomException, SecurityException {
        ((AdminFacade) accessManager.getSession(token, clientType, ClientType.ADMIN)).deleteCompany(companyId);
        return new ResponseEntity<>("Company with id " + companyId + " deleted", HttpStatus.OK);
    }

    @PostMapping("/coupons")
    public ResponseEntity<String> addCoupon(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestBody CouponDto coupon) throws CustomException, SecurityException {
        ((CompanyFacade) accessManager.getSession(token, clientType, ClientType.COMPANY)).addCoupon(couponModelMapper.dtoToEntity(coupon));
        return new ResponseEntity<>("Coupon " + coupon.getTitle() + " added", HttpStatus.CREATED);
    }

    @PutMapping("/coupons")
    public ResponseEntity<String> updateCoupon(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestBody CouponDto coupon) throws CustomException, SecurityException {
        ((CompanyFacade) accessManager.getSession(token, clientType, ClientType.COMPANY)).updateCoupon(couponModelMapper.dtoToEntity(coupon));
        return new ResponseEntity<>("Coupon with id " + coupon.getId() + " updated", HttpStatus.OK);
    }

    @DeleteMapping("/coupons/{couponId}")
    public ResponseEntity<String> deleteCoupon(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @PathVariable long couponId) throws CustomException, SecurityException {
        ((CompanyFacade) accessManager.getSession(token, clientType, ClientType.COMPANY)).deleteCoupon(couponId);
        return new ResponseEntity<>("Coupon with id " + couponId + " deleted", HttpStatus.OK);
    }

    @GetMapping("/coupons")
    public ResponseEntity<List<CouponDto>> getAllCoupons(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType) throws CustomException, SecurityException {
        List<CouponDto> coupons = couponModelMapper.entityToDto(((CompanyFacade) accessManager.getSession(token, clientType, ClientType.COMPANY)).getAllCoupons());
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping(value = "/coupons", params = "category")
    public ResponseEntity<List<CouponDto>> getAllCoupons(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestParam Category category) throws CustomException, SecurityException {
        List<CouponDto> coupons = couponModelMapper.entityToDto(((CompanyFacade) accessManager.getSession(token, clientType, ClientType.COMPANY)).getAllCoupons(category));
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping(value = "/coupons", params = "price")
    public ResponseEntity<List<CouponDto>> getAllCoupons(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestParam double price) throws CustomException, SecurityException {
        List<CouponDto> coupons = couponModelMapper.entityToDto(((CompanyFacade) accessManager.getSession(token, clientType, ClientType.COMPANY)).getAllCoupons(price));
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping("coupons/purchased")
    ResponseEntity<?> getPurchasedCoupons(@RequestHeader("Authorization") String token, @RequestHeader("clientType") ClientType clientType) throws CustomException, SecurityException {
        Map<Long, Long> coupons = ((CompanyFacadeImpl) accessManager.getSession(token, clientType, ClientType.COMPANY)).getPurchasedCoupons();
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }
}