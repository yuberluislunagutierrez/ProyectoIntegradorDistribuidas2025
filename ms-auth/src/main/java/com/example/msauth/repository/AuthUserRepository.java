package com.example.msauth.repository;

import com.example.msauth.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser,Integer> {


    Optional<AuthUser> findByUserName(String username);
    Optional<AuthUser> findById(Integer id);
}