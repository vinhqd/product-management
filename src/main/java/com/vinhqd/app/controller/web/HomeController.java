package com.vinhqd.app.controller.web;

import com.vinhqd.app.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @Autowired
    private

    @GetMapping("/")
    public String homePage() {
        return "web/home";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") UserDTO user) {
        return "auth/login";
    }

    @PostMapping("/register")
    public String registry(UserDTO userDTO) {

        return "redirect:/login";
    }

}
