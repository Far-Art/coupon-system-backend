package com.fa.CouponsMsProject.security.tokenization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.fa.CouponsMsProject.config.ApplicationConfig.IDLE_LOGIN_TIMEOUT_INTERVAL_MINUTES;

@Component
@NoArgsConstructor
public class TokenManager {

    /* holds token expiration time */
    @Getter
    private final Map<String, LocalDateTime> tokensMap = new HashMap<>();

    /* minutes to add to token expiration time */
    private final static int EXPIRATION_MINUTES_INTERVAL = IDLE_LOGIN_TIMEOUT_INTERVAL_MINUTES;

    /**
     * return idle value in milliseconds before system disconnects the client from access
     */
    public static int getExpirationInterval(){
        return EXPIRATION_MINUTES_INTERVAL * 60_000;
    }

    /**
     * Obtain new token
     */
    public String getNewToken() {
        String token = getRandomToken();
        tokensMap.put(token, getNewExpirationTime());
        return token;
    }

    /**
     * If removal was successful return true, otherwise false
     */
    public boolean removeToken(String token) {
        return tokensMap.remove(token) != null;
    }

    /**
     * this method activates everytime client makes request and prolongs token expiration time, return true if action was successful, return false if such token not exist
     */
    public boolean extendTokenTime(String token) {
        if (tokensMap.get(token) != null) {
            tokensMap.put(token, getNewExpirationTime());
            return true;
        }
        return false;
    }

    /**
     * check if token exist
     * @return boolean if exist
     */
    public boolean isExist(String token){
        return tokensMap.containsKey(token);
    }

    /* check if token expired */
    public boolean isExpired(String token){
        return tokensMap.get(token).isAfter(timeNow());
    }

    private LocalDateTime getNewExpirationTime() {
        return timeNow().plusMinutes(EXPIRATION_MINUTES_INTERVAL);
    }

    /* get current local time */
    private LocalDateTime timeNow() {
        return LocalDateTime.now();
    }

    /* create new token string from UUID */
    private String getRandomToken() {
        return UUID.randomUUID().toString();
    }
}