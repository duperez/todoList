package com.example.todolist.services.Interfaces;

import com.example.todolist.Exceptions.DuplicatedEmailException;
import com.example.todolist.Exceptions.DuplicatedUserException;
import com.example.todolist.objects.dtos.SingUpDto;
import com.example.todolist.objects.dtos.UserDto;

public interface AuthenticationServiceI {

    void singIn(UserDto loginDto);
    void singUp(SingUpDto signUpDto) throws DuplicatedUserException, DuplicatedEmailException;
    void logOut();


}
