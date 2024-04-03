package com.chandrasekahr.Spring_security_JWT.repository;

import com.chandrasekahr.Spring_security_JWT.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    Optional<UserInfo> findByName(String username);

    Optional<UserInfo> findByEmail(String username);
}
