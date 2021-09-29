package com.fa.CouponsMsProject.model.response;

import com.fa.CouponsMsProject.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientLoginResponseModel {
    private long id;
    private String email;
    private String name;
    private String lastName;
    private String token;
    private ClientType clientType;
    private boolean isActive;
    private long idleDisconnectIntervalInMillis;
}