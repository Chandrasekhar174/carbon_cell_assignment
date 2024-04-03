package com.chandrasekahr.Spring_security_JWT.entity;


import lombok.Data;

@Data
public class Entry {
    private String API;
    private String Description;
    private String Auth;
    private boolean HTTPS;
    private String Cors;
    private String Link;
    private String Category;
}
