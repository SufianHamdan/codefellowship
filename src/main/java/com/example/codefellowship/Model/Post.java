package com.example.codefellowship.Model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String body;

    @CreationTimestamp
    protected Date createdAt;

    @ManyToOne
    public ApplicationUser applicationUser;


    public Post(String body, ApplicationUser applicationUser) {
        this.body = body;
        this.applicationUser = applicationUser;
    }

    public Post(){ }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
