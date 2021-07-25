package com.example.codefellowship.Controller;

import com.example.codefellowship.Infrastructure.ApplicationUserRepository;
import com.example.codefellowship.Model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class userController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/userdetail")
    public String getUser(Principal p, Model m){
        try {
            ApplicationUser currentUser = applicationUserRepository.findUserByUserName(p.getName());
            m.addAttribute("displayedUser", currentUser);
        } catch(Exception e){
            System.out.println(e);
        }
        return "UserDetail";
    }
}
