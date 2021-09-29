package com.fa.CouponsMsProject.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.ToString.Exclude;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company extends Client {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String email;
	private String password;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	@Exclude
	private final List<Coupon> coupons = new ArrayList<>();

	@Column(columnDefinition = "boolean default false")
	private boolean isActive = false;

	public void addCoupon(Coupon coupon) {
		coupon.setCompany(this);
		coupons.add(coupon);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getLastName() {
		return null;
	}

	@Override
	public ClientType getClientType() {
		return ClientType.COMPANY;
	}
}