package com.fa.CouponsMsProject.security.access;

import com.fa.CouponsMsProject.beans.ClientType;
import com.fa.CouponsMsProject.dto.AccessDTO;
import com.fa.CouponsMsProject.exceptions.SecurityException;
import com.fa.CouponsMsProject.facades.ClientFacade;
import com.fa.CouponsMsProject.security.login.LoginManager;
import com.fa.CouponsMsProject.security.session.SessionManager;
import com.fa.CouponsMsProject.security.tokenization.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessManager {

    private final TokenManager tokenManager;

    private final LoginManager loginManager;

    private final SessionManager sessionManager;

    public AccessDTO login(String email, String password) throws SecurityException {
        AccessDTO accessDTO = new AccessDTO(loginManager.login(email, password), tokenManager.getNewToken());
        sessionManager.addNewSession(accessDTO.getToken(), accessDTO.getFacade());
        return accessDTO;
    }

    public boolean clientMadeAction(String token) throws SecurityException {
        if (!tokenManager.isExist(token) || !tokenManager.isExpired(token)) {
            throw new SecurityException("Your token is invalid, please re-login");
        }
        tokenManager.extendTokenTime(token);
        return true;
    }

    public String logout(String token) throws SecurityException {
        ClientFacade facade = sessionManager.deleteSession(token);
        boolean tokenRemoved = tokenManager.removeToken(token);
        if(facade == null && !tokenRemoved){
            throw new SecurityException("Such client not found");
        }
        return facade.getClient().getEmail();
    }

    public ClientFacade getSession(String token, ClientType clientType, ClientType requiredClientType) throws SecurityException {
        // token check
        if(!tokenManager.isExist(token)){
            throw new SecurityException("Your token is invalid, please login");
        }
        ClientFacade facade = sessionManager.getSession(token);
        // facade check
        if(facade == null){
            throw new SecurityException("Your session is expired, please login");
        }
        // client type check
        if(requiredClientType == ClientType.ANY){
            if(facade.getClientType() != clientType){
                throw new SecurityException("You are not authorized to do this action");
            }
        } else if(clientType != requiredClientType || facade.getClientType() != clientType){
            throw new SecurityException("You are not authorized to do this action");
        }
        return facade;
    }
}