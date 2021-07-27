package com.example.codefellowship.Controller;

import com.example.codefellowship.Infrastructure.ApplicationUserRepository;
import com.example.codefellowship.Model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class userController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/profile/{id}")
    public String getUser(@PathVariable long id, Model m){

        ApplicationUser currentUser = applicationUserRepository.findUserById(id);
        m.addAttribute("displayedUser", currentUser);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "profile";
    }


}
