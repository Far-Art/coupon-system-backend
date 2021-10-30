package com.fa.CouponsMsProject.dto;

import com.fa.CouponsMsProject.facades.ClientFacade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessDTO {
    private ClientFacade facade;
    private String token;
}
