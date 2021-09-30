package com.fa.CouponsMsProject.clr;

import com.fa.CouponsMsProject.beans.Admin;
import com.fa.CouponsMsProject.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
public class PushAdmins implements CommandLineRunner {

    private final AdminRepository adminRepository;

    @Override
    public void run(String... args) {

        Admin admin = Admin.builder().email("admin@admin.com").firstName("Artur").lastName("Farmanov")
                .department("Software").levelOfAccess(1).password("Admin123").build();

        Admin admin2 = Admin.builder().email("lusianafarmanov@gmail.com").firstName("Lusiana").lastName("Farmanov")
                .department("Economics").levelOfAccess(2).password("Asdf130621").build();

        adminRepository.saveAll(Arrays.asList(admin, admin2));

        System.out.println("Admins pushed to DB");
    }
}