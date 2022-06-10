package com.example.todolist.services.Interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsServiceI {

    UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException;

}
