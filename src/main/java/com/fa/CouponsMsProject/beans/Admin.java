package com.fa.CouponsMsProject.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "admins")
public class Admin extends Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String email;
	private String firstName;
	private String lastName;

	@JsonIgnore
	private String password;

	private String department;
	private int levelOfAccess;

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
		return ClientType.ADMIN;
	}
}
