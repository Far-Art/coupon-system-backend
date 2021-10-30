package com.fa.CouponsMsProject.facades;

import com.fa.CouponsMsProject.beans.Admin;
import com.fa.CouponsMsProject.beans.Company;
import com.fa.CouponsMsProject.beans.Customer;
import com.fa.CouponsMsProject.exceptions.CustomException;

import java.util.List;

public interface AdminFacade {

	void addCompany(Company company) throws CustomException;

	void updateCompany(Company company) throws CustomException;

	void deleteCompany(long companyId) throws CustomException;

	List<Company> getAllCompanies() throws CustomException;

	Company getCompany(long CompanyId) throws CustomException;

	void addCustomer(Customer customer) throws CustomException;

	void updateCustomer(Customer customer) throws CustomException;

	void deleteCustomer(long customerId) throws CustomException;

	List<Customer> getAllCustomers() throws CustomException;

	Customer getCustomer(long customerId) throws CustomException;
	
	Admin getMyInfo();
}
