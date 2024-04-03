package com.chandrasekahr.Spring_security_JWT.service;

import com.chandrasekahr.Spring_security_JWT.entity.UserInfo;
import com.chandrasekahr.Spring_security_JWT.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;
    public String addUser(UserInfo userInfo) {

        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetails=userInfoRepository.findByEmail(username);

        return userDetails.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found"+username));
    }

//    public String fetchAllData() {
//        String apiUrl="https://api.publicapis.org/entries";
//
//
//        return restTemplate.getForObject(apiUrl,String.class);
//    }




}
