package com.example.model;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Collections;

//import jakarta.persistence.*;
@Entity
@EnableAutoConfiguration
@Table(name = "employees")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",nullable = false,length = 50)
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @Column(name = "address",nullable = false,length = 50)
    @NotEmpty(message = "address cannot be empty")
    private String address;

    @Column(name = "dateOfJoining",nullable = false,length = 50)
    @NotEmpty(message = "dateOfJoining cannot be empty")
    private String dateOfJoining;

    @Column(name = "role")
//    @NotEmpty(message = "role cannot be empty")
    private String role;

    @Column(name = "pass")
    @NotEmpty(message = "pass cannot be empty")
    private String pass;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Employee() {
    }

    public Employee(String name, String address, String dateOfJoining,String pass,String role) {
        this.name = name;
        this.address = address;
        this.dateOfJoining = dateOfJoining;
        this.pass=pass;
        this.role=role;
    }

    public Employee(long id, String name, String address, String dateOfJoining,String pass, String role) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfJoining = dateOfJoining;
        this.pass=pass;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));//singleton design pattern
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

    @Override
    public String getUsername() {
        return this.name;
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
