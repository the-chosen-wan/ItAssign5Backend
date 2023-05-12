package com.net.assign5backend.entitity;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    String email;

    String password;

    String role;


    public UserEntity(){
        this.email = "";
        this.password = "";
        this.role = "";
    }
    
    public UserEntity(String email,String password,String role){
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getRole(){return role;}
    
    public void setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password=password;}
    public void setRole(String role){this.role=role;}
    
    public boolean validateEmail(){
        return email.contains("@");
    }
    
    public boolean validatePassword(){
        return password.length()>6;
    }
    
    public boolean isAdmin(){
        return this.role.equals("Admin");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+role));
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}