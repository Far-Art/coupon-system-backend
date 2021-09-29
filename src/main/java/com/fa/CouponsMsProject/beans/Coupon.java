package com.fa.CouponsMsProject.beans;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.ToString.Exclude;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = "id")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Company company;

	@Enumerated(EnumType.STRING)
	private Category category;

	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;

	@Column(length = 500)
	private String imageUrl;

	@ManyToMany
	@JoinTable(name = "customers_coupons", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@Exclude
	@JsonIgnoreProperties({"coupons"})
	private final Set<Customer> customers = new HashSet<>();

	public void addCustomer(Customer customer) {
        customers.add(customer);
	}
}