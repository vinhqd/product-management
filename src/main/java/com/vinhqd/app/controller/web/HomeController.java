package com.vinhqd.app.controller.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhqd.app.dto.UserDTO;
import com.vinhqd.app.utils.JsonParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @Autowired
    private JsonParserUtils jsonParserUtils;

    @GetMapping("/")
    public String homePage() {
        return "web/home";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") UserDTO user) {
        return "auth/login";
    }

}
