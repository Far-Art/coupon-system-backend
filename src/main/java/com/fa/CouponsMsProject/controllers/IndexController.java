package com.fa.CouponsMsProject.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import static com.fa.CouponsMsProject.security.constants.SecurityConstants.ALLOWED_HEADERS;
import static com.fa.CouponsMsProject.security.constants.SecurityConstants.ORIGINS;

//@RestController
//@CrossOrigin(origins = ORIGINS, allowedHeaders = ALLOWED_HEADERS)
public class IndexController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ModelAndView saveLeadQuery() {
        return new ModelAndView("forward:/");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
