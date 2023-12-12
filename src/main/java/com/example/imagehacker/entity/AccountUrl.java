package com.example.imagehacker.entity;

import jakarta.persistence.*;
/**
 * User.
 *
 * @author Nguyễn Hải
 * Created 27/11/2023
 */
@Entity
@Table(name = "account_url")
public class AccountUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "url")
    public String url;

    @ManyToOne
    @JoinColumn(name = "account")
    public Account account;

    @Column(name = "ma_xac_nhan")
    public String code;


    @Column(name = "luot_xem")
    public int views;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }


}