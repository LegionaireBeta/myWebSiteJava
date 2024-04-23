package com.website.mywebsite.services;

import com.website.mywebsite.dto.UserDto;

import java.util.List;


public interface UserService {
    UserDto getUser(String email);

    List<UserDto> getAllUsers();

}
