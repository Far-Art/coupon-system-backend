package com.fa.CouponsMsProject.dto;

import com.fa.CouponsMsProject.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private ClientType clientType;
    private String department;
    private int levelOfAccess;
    private boolean isActive;
}
