package com.fa.CouponsMsProject.beans;

public abstract class Client {

	public abstract long getId();

	public abstract String getEmail();

	public abstract String getPassword();

	public abstract String getName();

	public abstract String getLastName();

	public abstract ClientType getClientType();
}
