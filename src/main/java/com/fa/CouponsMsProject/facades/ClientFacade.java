package com.fa.CouponsMsProject.facades;

import com.fa.CouponsMsProject.beans.Client;
import com.fa.CouponsMsProject.beans.ClientType;
import lombok.Data;

@Data
public abstract class ClientFacade {

	protected ClientType clientType;

	public abstract Client getClient();

	public abstract Client getClient(long id);

	public abstract Client login(String email, String password);
}