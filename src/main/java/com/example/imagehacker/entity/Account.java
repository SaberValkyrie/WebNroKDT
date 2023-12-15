package com.example.imagehacker.entity;

import jakarta.persistence.*;
/**
 * User.
 *
 * @author Nguyễn Hải
 * Created 27/11/2023
 */
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long userId;

    @Column(name = "username")
    public String username;
    @Column(name = "password")
    public String password;
    @Column(name = "ip_address")
    public String ipAddress;
    @Column(name = "is_admin")
    public int role;
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


}