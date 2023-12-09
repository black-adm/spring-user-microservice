package com.ms.user.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;
import com.ms.user.services.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    
    final UserService userService;
    final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> create(@RequestBody @Valid UserRecordDto userRecordDto) {        
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel); 

        var userExists = userRepository.findByEmail(userModel.getEmail());

        if (userExists != null) {
            ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }
}
