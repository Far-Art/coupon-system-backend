package com.fa.CouponsMsProject.jobs;

import com.fa.CouponsMsProject.security.session.SessionManager;
import com.fa.CouponsMsProject.security.tokenization.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import static com.fa.CouponsMsProject.config.ApplicationConfig.EXPIRED_SESSION_REMOVAL_JOB_INTERVAL_MILLIS;

@Component
@RequiredArgsConstructor
public class ExpiredSessionRemovalJob {

    private LocalDateTime timeNow;
    private final SessionManager sessionManager;
    private final TokenManager tokenManager;

    @Scheduled(initialDelay = 3000, fixedRate = EXPIRED_SESSION_REMOVAL_JOB_INTERVAL_MILLIS)
    public void runExpiredSessionRemoval(){
        timeNow = LocalDateTime.now();
        tokenManager.getTokensMap().entrySet().removeIf(e -> (e.getValue().isBefore(timeNow) && sessionManager.getSessionMap().keySet().remove(e.getKey())));
    }
}