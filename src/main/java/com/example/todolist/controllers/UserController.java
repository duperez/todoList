package com.example.todolist.controllers;

import com.example.todolist.objects.models.UserModel;
import com.example.todolist.repositories.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    @RequestMapping("/all")
    List<UserModel> getuser() {
        return userRepository.findAll();
    }



}
