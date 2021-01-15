package com.vinhqd.app.controller.web;

import com.vinhqd.app.dto.UserDTO;
import com.vinhqd.app.exceptions.UserAlreadyExistException;
import com.vinhqd.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String homePage() {
        return "web/home";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") UserDTO user) {
        return "auth/login";
    }

    @PostMapping("/register")
    public String registry(Model model, @Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult) {
//        userService.save(userDTO);
        if (bindingResult.hasErrors()) {
            return "auth/login?registerErr";
        }
        try {
            userDTO = userService.save(userDTO);
        } catch (UserAlreadyExistException ex) {
            return "auth/login?registerErr";
        }
        return "auth/login";
    }

}
