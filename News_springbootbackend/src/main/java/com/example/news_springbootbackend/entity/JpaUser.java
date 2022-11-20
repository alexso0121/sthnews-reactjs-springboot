package com.example.news_springbootbackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "JpaUser")
public class JpaUser {

    @Id @GeneratedValue
   // @OneToOne(mappedBy = "JpaUser",fetch = FetchType.LAZY)
    private int id;
    private String username;
    private String password;

    private String email;
    private String roles;


    public JpaUser() {}

    public JpaUser(String username, String password,String email, String roles) {
        this.username = username;
        this.password = password;
        this.email=email;
        this.roles = roles;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SecurityUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
