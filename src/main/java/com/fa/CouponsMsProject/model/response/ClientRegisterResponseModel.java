package com.fa.CouponsMsProject.model.response;

import lombok.Data;

@Data
public class ClientRegisterResponseModel {
    private String email;
    private String message = "Client successfully registered";

    public ClientRegisterResponseModel(String email) {
        this.email = email;
    }
}