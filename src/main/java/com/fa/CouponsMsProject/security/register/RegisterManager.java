package com.fa.CouponsMsProject.security.register;

import com.fa.CouponsMsProject.beans.Client;
import com.fa.CouponsMsProject.beans.Company;
import com.fa.CouponsMsProject.beans.Customer;
import com.fa.CouponsMsProject.exceptions.CustomException;
import com.fa.CouponsMsProject.mappers.ClientModelMapper;
import com.fa.CouponsMsProject.model.request.ClientRegisterRequestModel;
import com.fa.CouponsMsProject.repositories.CompanyRepository;
import com.fa.CouponsMsProject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterManager {

    private final CustomerRepository customerRepository;

    private final CompanyRepository companyRepository;

    private final ClientModelMapper clientModelMapper;


    /**
     * Register new Client in the system, clients with company type must be approved before being added to database
     */
    public void register(ClientRegisterRequestModel request) throws CustomException {

        /* check if email already exist */
        if(customerRepository.existsByEmail(request.getEmail()) || companyRepository.existsByEmail(request.getEmail())){
            throw new CustomException("Such email already exists");
        }

        /* retrieve client */
        Client client = clientModelMapper.clientModelToEntity(request);

        if(client != null){
            switch (request.getClientType()){
                case CUSTOMER:
                    customerRepository.saveAndFlush((Customer) client);
                    break;
                case COMPANY:
                    companyRepository.saveAndFlush((Company) client);
                    break;
            }
        }
    }
}
