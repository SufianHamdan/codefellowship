package com.example.codefellowship.Infrastructure;

import com.example.codefellowship.Model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findUserByUserName (String username);
}
