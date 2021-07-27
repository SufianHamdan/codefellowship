package com.example.codefellowship.Infrastructure;

import com.example.codefellowship.Model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findUserByUserName (String username);
    ApplicationUser findUserById (Long id);
}
