package com.fa.CouponsMsProject.clr;

import com.fa.CouponsMsProject.beans.*;
import com.fa.CouponsMsProject.repositories.AdminRepository;
import com.fa.CouponsMsProject.repositories.CompanyRepository;
import com.fa.CouponsMsProject.repositories.CouponRepository;
import com.fa.CouponsMsProject.repositories.CustomerRepository;
import com.fa.CouponsMsProject.utils.generators.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//@Component
@Order(2)

/**
 * Initialize a Database with random values of customers, companies and coupons.
 * Also initializes a single bean of admin with fixed values. Used for testing
 * only.
 *
 * @author Far-Art
 *
 */
@RequiredArgsConstructor
public class PushDataForTest implements CommandLineRunner {

    private final CompanyGenerator companyGenerator;

    private final CompanyRepository companyRepository;

    private final CouponRepository couponRepository;

    private final CustomerRepository customerRepository;

    private final AdminRepository adminRepository;

    private static final int NUM_OF_CUSTOMERS = 4;

    private static final int NUM_OF_COMPANIES = 15;

    private static final Random RANDOM = new Random();

    private final int counter = 1;

    @Override
    public void run(String... args) {

        System.out.println("Dummy Data being pushed to DB, this may take some while...");

        List<Customer> customers = new ArrayList<>();
        List<Company> companies = new ArrayList<>();
        List<Coupon> coupons = new ArrayList<>();

        for (int i = 0; i < NUM_OF_CUSTOMERS; i++) {
            // add specific customer
            if (i == 0) {
                customers.add(
                        Customer.builder()
                                .firstName("artur")
                                .lastName("farmanov")
                                .email("artur@artur.com")
                                .password("1234aA")
                                .build());
            }
            // add random customer
            customers.add(
                    Customer.builder().firstName(ClientNameGenerator.getName()).lastName(ClientNameGenerator.getName())
                            .email(EmailGenerator.generateEmail(ClientNameGenerator.getSameName()))
                            .password(PasswordGenerator.getPassword()).build());
        }
        // add random company
        for (int i = 0; i < NUM_OF_COMPANIES ; i++) {
            companies.add(companyGenerator.generate());
        }

        Admin admin = Admin.builder().email("admin@admin.com").firstName("Artur").lastName("Farmanov")
                .department("Software").levelOfAccess(2).password("Admin123").build();

        customerRepository.saveAll(customers);

        companyRepository.saveAll(companies);

        couponRepository.saveAll(coupons);

        adminRepository.save(admin);

        System.out.println("Dummy Data pushed to DB");

    }

}
