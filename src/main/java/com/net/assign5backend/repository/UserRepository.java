package com.net.assign5backend.repository;

import com.net.assign5backend.entitity.UserEntity;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    public UserEntity findByEmail(String email);
    public UserEntity findByEmailAndPassword(String email, String password);
    public List<UserEntity> findByRole(String role);

    @Modifying
    @Transactional
    public void deleteByEmail(String email);
}
