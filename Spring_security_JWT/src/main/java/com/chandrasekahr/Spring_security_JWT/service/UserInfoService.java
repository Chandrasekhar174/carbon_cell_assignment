package com.chandrasekahr.Spring_security_JWT.service;

import com.chandrasekahr.Spring_security_JWT.entity.UserInfo;
import com.chandrasekahr.Spring_security_JWT.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addUser(UserInfo userInfo) {
        Optional<UserInfo> userInfo1=userInfoRepository.findByEmail(userInfo.getEmail());
        if(!userInfo1.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Already this email_id present please login");
        }
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return ResponseEntity.ok("Registration successfully");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetails=userInfoRepository.findByEmail(username);

        return userDetails.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found"+username));
    }

}
