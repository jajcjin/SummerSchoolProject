package com.example.demo.entity;

import lombok.Data;

import java.sql.*;

@Data
public class Proposal {
    private Integer ppid;
    private String author;
    private String ppname;
    private String ppcontent;
    private String status;
    private String vote;
    private String disvote;

    public void setPpid(Integer ppid){
        this.ppid = ppid;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setPpname(String ppname){
        this.ppname = ppname;
    }
    public void setPpcontent(String ppcontent){
        this.ppcontent = ppcontent;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setVote(String vote){
        this.vote = vote;
    }
    public void setDisvote(String disvote){
        this.disvote = disvote;
    }

    public String getPpname() {
        return ppname;
    }
    public String getStatus() {
        return status;
    }
    public String getVote() {
        return vote;
    }
    public String getDisvote(){
        return disvote;
    }
}
