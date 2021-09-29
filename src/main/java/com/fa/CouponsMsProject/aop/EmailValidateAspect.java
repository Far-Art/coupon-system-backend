package com.fa.CouponsMsProject.aop;

import com.fa.CouponsMsProject.beans.Client;
import com.fa.CouponsMsProject.exceptions.CustomException;
import com.fa.CouponsMsProject.repositories.AdminRepository;
import com.fa.CouponsMsProject.repositories.CompanyRepository;
import com.fa.CouponsMsProject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class EmailValidateAspect {
	private final CompanyRepository companyRepository;

	private final CustomerRepository customerRepository;

	private final AdminRepository adminRepository;

	private final int emailLength = 6;

//	@Before("execution(* com.fa.CouponsMsProject.services.impl.AdminFacadeImpl.add*(..)) || execution(* com.fa.CouponsMsProject.services.impl.AdminFacadeImpl.update*(..))")
	@Before("@annotation(EmailValidate)")
	public void checkEmail(JoinPoint jp) throws CustomException {

		String email = null;
		Object object = jp.getArgs()[0];

		/* chars that email must contain */
		CharSequence charSeq1 = "@";
		CharSequence charSeq2 = ".";

		if (object instanceof Client) {
			email = ((Client) object).getEmail();
		} else if (object instanceof String) {
			email = (String) object;
		}

		if (email != null) {

			/* check pattern */
			if (!email.contains(charSeq1) || !email.contains(charSeq2) || email.length() < emailLength) {
				throw new CustomException("Bad Email Pattern");
			}

			if (jp.getSignature().getName().toLowerCase().contains("update") && object instanceof Client) {

				String clientType = object.getClass().getSimpleName().toLowerCase();
				long clientId = ((Client) object).getId();

				/* check if email changed */
				if (clientType.contains("admin") && !adminRepository.existsByIdAndEmail(clientId, email)) {
					checkExistence(email);
				} else if (clientType.contains("customer") && !customerRepository.existsByIdAndEmail(clientId, email)) {
					checkExistence(email);
				} else if (clientType.contains("company") && !companyRepository.existsByIdAndEmail(clientId, email)) {
					checkExistence(email);
				}

			} else {
				checkExistence(email);
			}

		}

	}

	/* check email existence */
	private void checkExistence(String email) throws CustomException {
		boolean adminEmail = adminRepository.existsByEmail(email);
		boolean custEmail = customerRepository.existsByEmail(email);
		boolean compEmail = companyRepository.existsByEmail(email);

		if (adminEmail || custEmail || compEmail) {
			throw new CustomException("Such email (" + email + ") already taken");
		}
	}
}
