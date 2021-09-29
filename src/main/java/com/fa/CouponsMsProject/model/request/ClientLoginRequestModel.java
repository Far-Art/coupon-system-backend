package com.fa.CouponsMsProject.model.request;

import lombok.Data;

@Data
public class ClientLoginRequestModel {
    private String email;
    private String password;
}