package com.fa.CouponsMsProject.clr;

import com.fa.CouponsMsProject.beans.Company;
import com.fa.CouponsMsProject.beans.Customer;
import com.fa.CouponsMsProject.repositories.CompanyRepository;
import com.fa.CouponsMsProject.repositories.CustomerRepository;
import com.fa.CouponsMsProject.utils.generators.ClientNameGenerator;
import com.fa.CouponsMsProject.utils.generators.CompanyGenerator;
import com.fa.CouponsMsProject.utils.generators.EmailGenerator;
import com.fa.CouponsMsProject.utils.generators.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Initialize a Database with random values of customers, companies and coupons.
 * Also initializes a single bean of admin with fixed values. Used for testing
 * only.
 *
 * @author Far-Art
 */

@Component
@Order(3)
@Profile({"dev", "prod"})
@RequiredArgsConstructor
public class PushDemoData implements CommandLineRunner {

    private static final int NUM_OF_CUSTOMERS = 2;
    private static final int NUM_OF_COMPANIES = 7;

    private final CompanyGenerator companyGenerator;

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        if (customerRepository.count() <= 1) {
            List<Customer> customers = IntStream.range(0, NUM_OF_CUSTOMERS).mapToObj(i -> Customer.builder().firstName(ClientNameGenerator.getName()).lastName(ClientNameGenerator.getName())
                    .email(EmailGenerator.generateEmail(ClientNameGenerator.getSameName()))
                    .password(PasswordGenerator.getPassword()).build()).collect(Collectors.toList());
            customerRepository.saveAll(customers);
            System.out.println("Customers pushed to DB");
        } else {
            System.out.println("Customers present in DB");
        }

        if (companyRepository.count() <= 1) {
            List<Company> companies = IntStream.range(0, NUM_OF_COMPANIES).mapToObj(i -> companyGenerator.generate()).collect(Collectors.toList());
            companyRepository.saveAll(companies);
            System.out.println("Companies pushed to DB");
        } else {
            System.out.println("Companies present in DB");
        }

    }

}
