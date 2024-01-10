package com.springjava.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springjava.dto.UserRegisterDto;
import com.springjava.entity.User;

public interface UserService extends UserDetailsService {
User save(UserRegisterDto userRegDto);
}
