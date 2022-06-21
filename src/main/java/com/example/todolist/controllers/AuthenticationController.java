package com.example.todolist.controllers;

import com.example.todolist.Exceptions.DuplicatedEmailException;
import com.example.todolist.Exceptions.DuplicatedUserException;
import com.example.todolist.objects.dtos.SingUpDto;
import com.example.todolist.objects.dtos.UserDto;
import com.example.todolist.services.Interfaces.AuthenticationServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    AuthenticationServiceI authenticationService;

    @PostMapping(path = "/signin", consumes = "application/json")
    public ResponseEntity<String> authenticateUser(@RequestBody UserDto loginDto){
        log.info("trying to log in");
        authenticationService.singIn(loginDto);
        log.info("log in success");
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping(path = "/signin", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<UserDto> authenticateUserUrlEncoded(UserDto loginDto){
        log.info("trying to log in");
        authenticationService.singIn(loginDto);
        log.info("log in success");
        return new ResponseEntity<>(loginDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String>logoutUser(){
        authenticationService.logOut();
        return new ResponseEntity<>("User logout successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SingUpDto signUpDto){

        try {
            authenticationService.singUp(signUpDto);
        } catch (DuplicatedUserException exception) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        } catch (DuplicatedEmailException exception) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

}
