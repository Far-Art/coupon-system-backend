package com.fa.CouponsMsProject.dto;

import com.fa.CouponsMsProject.beans.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponDto {

    private long id;
    private String companyName;
    private String companyEmail;
    private Company companyEntity;
    private String category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private double amount;
    private double price;
    private String imageUrl;
}
