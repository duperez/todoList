package com.example.todolist.services.implementations;

import com.example.todolist.Exceptions.DuplicatedEmailException;
import com.example.todolist.Exceptions.DuplicatedUserException;
import com.example.todolist.objects.dtos.SingUpDto;
import com.example.todolist.objects.dtos.UserDto;
import com.example.todolist.objects.models.RoleModel;
import com.example.todolist.objects.models.UserModel;
import com.example.todolist.repositories.RoleRepository;
import com.example.todolist.repositories.UserRepository;
import com.example.todolist.services.Interfaces.AuthenticationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class AuthenticationServiceImp implements AuthenticationServiceI {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void singIn(UserDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getLogin(), loginDto.getPass()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void singUp(SingUpDto signUpDto) throws DuplicatedUserException, DuplicatedEmailException {
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            throw new DuplicatedUserException();
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new DuplicatedEmailException();
        }

        UserModel user = new UserModel();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        RoleModel roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);
    }

    @Override
    public void logOut() {
        SecurityContextHolder.clearContext();
    }
}
