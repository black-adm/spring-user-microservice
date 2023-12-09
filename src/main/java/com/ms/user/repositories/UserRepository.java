package com.ms.user.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.user.models.UserModel;
import java.util.List;


public interface UserRepository extends JpaRepository<UserModel, UUID> {
    List<UserModel> findByEmail(String email);
}
