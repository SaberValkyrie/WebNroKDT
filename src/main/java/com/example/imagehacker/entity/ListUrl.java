package com.example.imagehacker.entity;

import jakarta.persistence.*;
/**
 * User.
 *
 * @author Nguyễn Hải
 * Created 27/11/2023
 */
@Entity
@Table(name = "account_list_url")
public class ListUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "link_rut_gon")
    public String linkRutGon;
    @Column(name = "time_add")
    public String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkRutGon() {
        return linkRutGon;
    }

    public void setLinkRutGon(String linkRutGon) {
        this.linkRutGon = linkRutGon;
    }

    public byte getKhadung() {
        return khadung;
    }

    public void setKhadung(byte khadung) {
        this.khadung = khadung;
    }

    @Column(name = "kha_dung")
    public byte khadung;

}