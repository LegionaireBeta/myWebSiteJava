package com.website.mywebsite.controllers;


import com.website.mywebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/myProfile")
    public String getProfile(Model model, @AuthenticationPrincipal UserDetails userDetails){
        model.addAttribute("userName", userService.getUser(userDetails.getUsername()));
        return "profile_page";
    }

}
