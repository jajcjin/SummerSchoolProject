package com.example.demo.entity;

import lombok.Data;
import java.sql.*;


@Data
public class User {

    private Integer id;
    private String username;
    private String gender;
    private Date birthday;
    private String address;
    private String email;
    private String community;
    private String org;
    private String passwd;
	private String vip;

    public void setUsername(String username){
        this.username = username;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCommunity(String community) {
        this.community = community;
    }
    public void setOrg(String org) {
        this.org = org;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public void setVip(String vip){
        this.vip = vip;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public String getPasswd() {
        return passwd;
    }
    public String getUsername() {
        return username;
    }
    public String getVip(){
        return vip;
    }
}
