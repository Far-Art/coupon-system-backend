package com.fa.CouponsMsProject.facades.impl;

import com.fa.CouponsMsProject.aop.EmailValidate;
import com.fa.CouponsMsProject.beans.*;
import com.fa.CouponsMsProject.exceptions.CustomException;
import com.fa.CouponsMsProject.repositories.AdminRepository;
import com.fa.CouponsMsProject.repositories.CompanyRepository;
import com.fa.CouponsMsProject.repositories.CouponRepository;
import com.fa.CouponsMsProject.repositories.CustomerRepository;
import com.fa.CouponsMsProject.facades.AdminFacade;
import com.fa.CouponsMsProject.facades.ClientFacade;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants.Exclude;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class AdminFacadeImpl extends ClientFacade implements AdminFacade {

	@Exclude
	private Admin admin;

	@Exclude
	private final CompanyRepository companyRepository;

	@Exclude
	private final CouponRepository couponRepository;

	@Exclude
	private final CustomerRepository customerRepository;

	@Exclude
	private final AdminRepository adminRepository;

	@Override
	public Client getClient() {
		return admin;
	}

	@Override
	public Client getClient(long id) {
		admin = adminRepository.findById(id).get();
		return admin;
	}

	@Override
	public Client login(String email, String password) {
		admin = adminRepository.findByEmailAndPassword(email, password);
		clientType = ClientType.ADMIN;
		return admin;
	}

	@Override
	@EmailValidate
	public void addCompany(Company company) throws CustomException {

		/* check if such company exist by name */
		if (companyRepository.existsByName(company.getName())) {
			throw new CustomException("Company with name(" + company.getName() + ") already exist");
		}

		validateCompany(company);

		/* save company */
		companyRepository.save(company);
	}

	@Override
	@EmailValidate
	public void updateCompany(Company company) throws CustomException {

		validateCompany(company);

		/* fetch company from database */
		Company toCheck = companyRepository.getOne(company.getId());

		/* check if name was changed */
		if (!company.getName().equalsIgnoreCase(toCheck.getName())) {
			throw new CustomException("Updating company's name is now allowed");
		}

		/* save company to database */
		companyRepository.save(company);
	}

	@Override
	public void deleteCompany(long companyId) throws CustomException {

		/* check if company exist */
		if (!companyRepository.existsById(companyId)) {
			throw new CustomException("Such company not found");
		}
		/* delete company */
		companyRepository.deleteById(companyId);

	}

	@Override
	public List<Company> getAllCompanies() throws CustomException {
		/* fetch all companies */
		List<Company> companies = companyRepository.findAll();

		/* check if there are any companies */
		if (companies.isEmpty()) {
			throw new CustomException("No companies found, consider adding new companies to database");
		}
		return companies;
	}

	@Override
	public Company getCompany(long CompanyId) throws CustomException {
		/* check if there is no such company */
		if (!companyRepository.existsById(CompanyId)) {
			throw new CustomException("Such company not found");
		}
		return companyRepository.getOne(CompanyId);
	}

	@Override
	@EmailValidate
	public void addCustomer(Customer customer) throws CustomException {
		/* check if such customer exist */
		if (customerRepository.existsById(customer.getId())) {
			throw new CustomException("Such customer already exist");
		}
		validateCustomer(customer);
		customerRepository.save(customer);
	}

	@Override
	@EmailValidate
	public void updateCustomer(Customer customer) throws CustomException {
		validateCustomer(customer);
		customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(long customerId) throws CustomException {

		/* check if customer exist */
		if (!customerRepository.existsById(customerId)) {
			throw new CustomException("Such customer with id(" + customerId + ") not found");
		}
		/* delete customer */
		customerRepository.deleteById(customerId);

		/* delete relations to customer */
		couponRepository.deleteAllRelations(customerId);

	}

	@Override
	public List<Customer> getAllCustomers() throws CustomException {

		/* fetch all customers */
		List<Customer> customers = customerRepository.findAll();

		/* check if there are any customers */
		if (customers.isEmpty()) {
			throw new CustomException("No customers found, consider adding new customers to database");
		}
		return customers;
	}

	@Override
	public Customer getCustomer(long customerId) throws CustomException {

		/* check if such customer exist */
		if (!customerRepository.existsById(customerId)) {
			throw new CustomException("Such customer with id(" + customerId + ") not found");
		}
		return customerRepository.getOne(customerId);
	}

	@Override
	public Admin getMyInfo() {
		return adminRepository.findById(admin.getId()).get();
	}

	private void validateCustomer(Customer customer) throws CustomException {
		/* check if such customer exist */
		if (!customerRepository.existsById(customer.getId())) {
			throw new CustomException(
					"Such customer with id(" + customer.getId() + ") not found");
		} else if (customer.getLastName().length() < 1 || customer.getFirstName().length() < 1){
			throw new CustomException("Customers name must include at least 1 character");
		} else if (customer.getLastName().length() > 60 || customer.getFirstName().length() > 60){
			throw new CustomException("Customers name cannot exceed 60 characters");
		} else if (customer.getEmail().length() < 4){
			throw new CustomException("Customers email must include at least 4 characters");
		} else if (customer.getEmail().length() > 50){
			throw new CustomException("Customers email cannot exceed 50 characters");
		} else if (customer.getPassword().length() > 16){
			throw new CustomException("Customers password cannot exceed 16 characters");
		}
	}

	private void validateCompany(Company company) throws CustomException {
		/* check if such company exist */
		if (!companyRepository.existsById(company.getId())) {
			throw new CustomException(
					"Company with name(" + company.getName() + ") or email(" + company.getEmail() + ") already exist");
		} else if (company.getName().length() < 2){
			throw new CustomException("Companies name must include at least 2 characters");
		} else if (company.getName().length() > 200){
			throw new CustomException("Companies name cannot exceed 200 characters");
		} else if (company.getEmail().length() < 4){
			throw new CustomException("Companies email must include at least 4 characters");
		} else if (company.getEmail().length() > 50){
			throw new CustomException("Companies email cannot exceed 50 characters");
		} else if (company.getPassword().length() > 16){
			throw new CustomException("Companies password cannot exceed 16 characters");
		}
	}
}
