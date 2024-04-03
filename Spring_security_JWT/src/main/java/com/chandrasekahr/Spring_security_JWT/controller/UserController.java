package com.chandrasekahr.Spring_security_JWT.controller;

import com.chandrasekahr.Spring_security_JWT.entity.AuthRequest;
import com.chandrasekahr.Spring_security_JWT.entity.Entry;
import com.chandrasekahr.Spring_security_JWT.entity.UserInfo;
import com.chandrasekahr.Spring_security_JWT.service.DataEntryService;
import com.chandrasekahr.Spring_security_JWT.service.Jwtservice;
import com.chandrasekahr.Spring_security_JWT.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Jwtservice jwtservice;
    @Autowired
    private UserInfoService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataEntryService dataEntryService;

    @GetMapping("/home")
    public String getUser()
    {
        return "This is home page";

    }
    @PostMapping("/registration")
    public String registration(@RequestBody UserInfo userInfo)
    {
        return service.addUser(userInfo);
    }
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest)
    {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        if(authentication.isAuthenticated())
        {
            return jwtservice.generateToken(authRequest.getEmail());
        }
        else
        {
            throw new UsernameNotFoundException("user not found");
        }
    }


    @DeleteMapping("/logout")
    public String logout()
    {
        return "Logout successfully";
    }
    @GetMapping("/fetchFilterData")
    public List<Entry> getCategories(@RequestParam String category,@RequestParam Integer limit) throws NoSuchFieldException {
        return dataEntryService.filterdata(category,limit);
    }





}
