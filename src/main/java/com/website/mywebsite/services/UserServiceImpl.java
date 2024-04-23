package com.website.mywebsite.services;

import com.website.mywebsite.dto.UserDto;
import com.website.mywebsite.models.User;
import com.website.mywebsite.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

import static com.website.mywebsite.dto.UserDto.usersList;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDto getUser(String email) {
        return usersRepository.findByEmail(email)
                .map(UserDto::from)
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return usersList(usersRepository.findAll(Sort.by(Sort.Direction.ASC, "userId")));
    }


}
