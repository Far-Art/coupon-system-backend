package com.fa.CouponsMsProject.beans;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.ToString.Exclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class Customer extends Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	@ManyToMany
	@JoinTable(name = "customers_coupons", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "coupon_id"))
	@Exclude
	@JsonIgnore
	private final Set<Coupon> coupons = new HashSet<>();

	/**
	 * add relations on both ends
	 */
	public void addCoupon(Coupon coupon) {
		coupons.add(coupon);
		coupon.addCustomer(this);
	}

	@Override
	public String getName() {
		return firstName;
	}

	@Override
	public String getLastName(){
		return lastName;
	}

	@Override
	public ClientType getClientType() {
		return ClientType.CUSTOMER;
	}
}
