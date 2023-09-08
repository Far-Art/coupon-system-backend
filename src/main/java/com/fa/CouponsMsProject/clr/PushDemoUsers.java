package com.fa.CouponsMsProject.clr;

import com.fa.CouponsMsProject.beans.Admin;
import com.fa.CouponsMsProject.beans.Company;
import com.fa.CouponsMsProject.beans.Customer;
import com.fa.CouponsMsProject.repositories.AdminRepository;
import com.fa.CouponsMsProject.repositories.CompanyRepository;
import com.fa.CouponsMsProject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Initialize a Database with random values of customers, companies and coupons.
 * Also initializes a single bean of admin with fixed values. Used for testing
 * only.
 *
 * @author Far-Art
 */

@Component
@Order(2)
@Profile({"dev", "prod"})
@RequiredArgsConstructor
public class PushDemoUsers implements CommandLineRunner {

    private final AdminRepository adminRepository;

    private final CustomerRepository customerRepository;

    private final CompanyRepository companyRepository;

    @Override
    public void run(String... args) {
        Admin admin = Admin.builder()
                .email("demo@admin.com")
                .firstName("Demo")
                .lastName("Admin")
                .department("Software")
                .levelOfAccess(1)
                .password("Admin123")
                .build();

        adminRepository.save(admin);
        System.out.println("Demo admin pushed to DB");

        Customer customer = Customer.builder()
                .email("demo@customer.com")
                .firstName("Demo")
                .lastName("Customer")
                .password("Customer123")
                .build();

        customerRepository.save(customer);
        System.out.println("Demo admin pushed to DB");

        Company company = Company.builder()
                .name("Demo company")
                .isActive(true)
                .email("demo@company.com")
                .password("Company123")
                .build();

        companyRepository.save(company);
        System.out.println("Demo company pushed to DB");
    }
}