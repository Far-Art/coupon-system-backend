package com.fa.CouponsMsProject.security.session;

import com.fa.CouponsMsProject.facades.ClientFacade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class SessionManager {

    @Getter
    private final Map<String, ClientFacade> sessionMap = new HashMap<>();

    public String addNewSession(String token, ClientFacade facade){
        sessionMap.put(token, facade);
        return "success";
    }

    public ClientFacade getSession(String token){
        return sessionMap.get(token);
    }

    public ClientFacade deleteSession(String token){
        return sessionMap.remove(token);
    }
}