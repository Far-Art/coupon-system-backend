package com.fa.CouponsMsProject.controllers;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.beans.ClientType;
import com.fa.CouponsMsProject.beans.Customer;
import com.fa.CouponsMsProject.dto.CouponDto;
import com.fa.CouponsMsProject.exceptions.CustomException;
import com.fa.CouponsMsProject.exceptions.SecurityException;
import com.fa.CouponsMsProject.facades.AdminFacade;
import com.fa.CouponsMsProject.facades.CustomerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fa.CouponsMsProject.security.constants.SecurityConstants.ALLOWED_HEADERS;
import static com.fa.CouponsMsProject.security.constants.SecurityConstants.ORIGINS;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = ORIGINS, allowedHeaders = ALLOWED_HEADERS)
public class CustomerController extends ClientController {

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType) throws SecurityException, CustomException {
        return new ResponseEntity<>(((AdminFacade) accessManager.getSession(token, clientType, ClientType.ADMIN)).getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestBody Customer customer) throws CustomException, SecurityException {
        ((AdminFacade) accessManager.getSession(token, clientType, ClientType.ADMIN)).addCustomer(customer);
        return new ResponseEntity<>("Customer " + customer.getEmail() + " added", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestBody Customer customer) throws CustomException, SecurityException {
        ((AdminFacade) accessManager.getSession(token, clientType, ClientType.ADMIN)).updateCustomer(customer);
        return new ResponseEntity<>("Customer " + customer.getEmail() + " updated", HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @PathVariable long customerId) throws CustomException, SecurityException {
        ((AdminFacade) accessManager.getSession(token, clientType, ClientType.ADMIN)).deleteCustomer(customerId);
        return new ResponseEntity<>("Customer with id " + customerId + " deleted", HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> buyCoupons(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestBody List<Long> coupons) throws CustomException, SecurityException {
        int numOfPurchased = ((CustomerFacade) accessManager.getSession(token, clientType, ClientType.CUSTOMER)).buyCoupons(coupons);
        return new ResponseEntity<>("You bought " + numOfPurchased + (numOfPurchased == 1 ? " coupon" : " coupons"), HttpStatus.OK);
    }

    @GetMapping("/coupons")
    ResponseEntity<List<CouponDto>> getMyCoupons(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType) throws SecurityException {
        List<CouponDto> coupons = couponModelMapper.entityToDto(((CustomerFacade) accessManager.getSession(token, clientType, ClientType.CUSTOMER)).getMyCoupons());
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping(value = "/coupons", params = "category")
    ResponseEntity<List<CouponDto>> getMyCoupons(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestParam Category category) throws CustomException, SecurityException {
        List<CouponDto> coupons = couponModelMapper.entityToDto(((CustomerFacade) accessManager.getSession(token, clientType, ClientType.CUSTOMER)).getMyCoupons(category));
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping(value = "/coupons", params = "price")
    ResponseEntity<List<CouponDto>> getMyCoupons(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType, @RequestParam double price) throws CustomException, SecurityException {
        List<CouponDto> coupons = couponModelMapper.entityToDto(((CustomerFacade) accessManager.getSession(token, clientType, ClientType.CUSTOMER)).getMyCoupons(price));
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @DeleteMapping("/coupons/purchased")
    ResponseEntity<String> dismissMyCoupons(@RequestHeader("authorization") String token, @RequestHeader("clientType") ClientType clientType) throws SecurityException {
        ((CustomerFacade) accessManager.getSession(token, clientType, ClientType.CUSTOMER)).dismissAllCoupons();
        return new ResponseEntity<>("All your coupons dismissed", HttpStatus.OK);
    }
}