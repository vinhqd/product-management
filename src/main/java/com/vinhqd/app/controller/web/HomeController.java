package com.vinhqd.app.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "web/home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

}
