package com.example.codefellowship.Controller;

import com.example.codefellowship.Infrastructure.ApplicationUserRepository;
import com.example.codefellowship.Model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public class signUpController {


    @RequestMapping("/signup")
    public String signupPage(){
        return "SignUp";
    }

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView createUSer(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam Date date,
                                   @RequestParam String bio){
        password = passwordEncoder.encode(password);

        ApplicationUser user = new ApplicationUser(username, password, firstName, lastName, date, bio);
        applicationUserRepository.save(user);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          user, null, user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
        return new RedirectView("/userDetail");
    }

    @GetMapping("/signup")
    public String getSignup(Principal p, Model m) {
        try {
            ApplicationUser currentUser = applicationUserRepository.findUserByUserName(p.getName());
            if (currentUser != null) {
                new RedirectView("/userdetail");
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("user invalid");
        }
        return "signup";
    }
}

