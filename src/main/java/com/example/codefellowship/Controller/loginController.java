package com.example.codefellowship.Controller;

import com.example.codefellowship.Infrastructure.ApplicationUserRepository;
import com.example.codefellowship.Model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class loginController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/login")
    public String getLogin(Principal p, Model m) {
        try {
            ApplicationUser currentUser = applicationUserRepository.findUserByUserName(p.getName());
            if (currentUser != null) {
                new RedirectView("/UserDetail");
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println(57);
        }
        return "login";
    }
}
