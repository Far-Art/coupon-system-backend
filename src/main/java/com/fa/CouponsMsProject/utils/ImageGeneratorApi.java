package com.fa.CouponsMsProject.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ImageGeneratorApi {

    private final RestTemplate restTemplate;

    public String getRandomImageByCategory(int height, int width, String category) {
        try {
            Thread.sleep(250); // make thread sleep to prevent requests burst
        } catch (InterruptedException e) {
        }
        String randomImageUrl = String.format("https://source.unsplash.com/%sx%s/?%s", width, height, category);
        return restTemplate.headForHeaders(randomImageUrl).getLocation().toString();
    }
}
