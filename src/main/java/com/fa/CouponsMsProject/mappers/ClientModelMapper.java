package com.fa.CouponsMsProject.mappers;

import com.fa.CouponsMsProject.beans.*;
import com.fa.CouponsMsProject.dto.ClientDto;
import com.fa.CouponsMsProject.model.request.ClientRegisterRequestModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ClientModelMapper {

    public Client clientModelToEntity(ClientRegisterRequestModel model){
        switch (model.getClientType()){
            case CUSTOMER:
                Customer customer = new Customer();
                customer.setEmail(model.getEmail());
                customer.setPassword(model.getPassword());
                customer.setFirstName(model.getName());
                customer.setLastName(model.getLastName());
                return customer;
            case COMPANY:
                Company company = new Company();
                company.setEmail(model.getEmail());
                company.setPassword(model.getPassword());
                company.setName(model.getName());
                company.setActive(false);
                return company;
        }
        return null;
    }

    public ClientDto entityToDto(Client client){
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .password(client.getPassword())
                .clientType(client.getClientType())
                .department(client.getClientType() == ClientType.ADMIN ? ((Admin)client).getDepartment() : null)
                .levelOfAccess(client.getClientType() == ClientType.ADMIN ? ((Admin)client).getLevelOfAccess() : -999)
                .isActive(client.getClientType() == ClientType.COMPANY ? ((Company)client).isActive() : true)
                .build();
    }
}