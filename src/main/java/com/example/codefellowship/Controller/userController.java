package com.example.codefellowship.Controller;

import com.example.codefellowship.Infrastructure.ApplicationUserRepository;
import com.example.codefellowship.Model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

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

    @PostMapping("/follow/{id}")
    public RedirectView getFollowerPosts(Principal currentUSer,@PathVariable Long id){

        ApplicationUser user = applicationUserRepository.findUserByUserName(currentUSer.getName());
        ApplicationUser followedUser = applicationUserRepository.findUserById(id);

        followedUser.getFollowers().add(user);
        applicationUserRepository.save(followedUser);

        return new RedirectView("/");
    }

    @GetMapping("/feed")
    public String getAllPosts(Principal p, Model model){
        model.addAttribute("userObj", applicationUserRepository.findUserByUserName(p.getName()));
        model.addAttribute("displayedUser", applicationUserRepository.findUserByUserName(p.getName()));
        return "feed";
    }

    @GetMapping("/user/{id}")
    public String getUserProfile(@PathVariable Long id, Model model){
        ApplicationUser user = applicationUserRepository.findUserById(id);
        model.addAttribute("displayedUser", user);
        return "profile";
    }





}
