package com.fa.CouponsMsProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class CouponsMsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponsMsProjectApplication.class, args);
		System.out.println("Everything loaded OK");
	}

}
