package com.fa.CouponsMsProject.model.request;

import com.fa.CouponsMsProject.beans.ClientType;
import lombok.Data;

@Data
public class ClientRegisterRequestModel {
    private String email;
    private String password;
    private String name;
    private String lastName;
    private ClientType clientType;
}
