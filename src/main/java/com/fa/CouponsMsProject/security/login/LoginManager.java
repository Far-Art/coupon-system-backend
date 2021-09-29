package com.fa.CouponsMsProject.security.login;

import com.fa.CouponsMsProject.beans.ClientType;
import com.fa.CouponsMsProject.exceptions.SecurityException;
import com.fa.CouponsMsProject.repositories.AdminRepository;
import com.fa.CouponsMsProject.repositories.CompanyRepository;
import com.fa.CouponsMsProject.repositories.CustomerRepository;
import com.fa.CouponsMsProject.facades.*;
import com.fa.CouponsMsProject.facades.impl.AdminFacadeImpl;
import com.fa.CouponsMsProject.facades.impl.CompanyFacadeImpl;
import com.fa.CouponsMsProject.facades.impl.CustomerFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginManager {

	/* Repositories */
	private final CompanyRepository companyRepository;

	private final CustomerRepository customerRepository;

	private final AdminRepository adminRepository;

	/* ApplicationContext */
	private final ApplicationContext ctx;

	public ClientFacade login(String email, String password) throws SecurityException {

		/* check if empty */
		if(email == null || email.isBlank() || password == null || password.isBlank()){
			throw new SecurityException("Email or Password cannot be empty");
		}

		/* check if contains whitespace */
		if(email.contains(" ") || password.contains(" ")){
			throw new SecurityException("Email or Password cannot contain whitespaces");
		}

		/* declare client facade */
		ClientFacade clientFacade = null;

		/* try to fetch customer account */
		if (customerRepository.existsByEmailAndPassword(email, password)) {
			clientFacade = (ClientFacade) ctx.getBean(CustomerFacade.class);
		}

		/* try to fetch company account */
		else if (companyRepository.existsByEmailAndPassword(email, password)) {
			clientFacade = (ClientFacade) ctx.getBean(CompanyFacade.class);
		}

		/* try to fetch admin account */
		else if (adminRepository.existsByEmailAndPassword(email, password)) {
			clientFacade = (ClientFacade) ctx.getBean(AdminFacade.class);
		}

		/* set client inside facade if not null */
		if(clientFacade != null){
			clientFacade.login(email, password);
			return clientFacade;
		}

		throw new SecurityException("Wrong Email or Password");
	}
}