package com.website.mywebsite.services;

import com.website.mywebsite.dto.UserForm;
import com.website.mywebsite.models.Role;
import com.website.mywebsite.models.State;
import com.website.mywebsite.models.User;
import com.website.mywebsite.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SmsService smsService;

    @Autowired
    private MailService mailService;

    @Override
    public void addUser(UserForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .password(passwordEncoder.encode(form.getPassword()))
                .phone(form.getPhone())
                .role(Role.USER)
                .state(State.CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .build();

        usersRepository.save(user);
        //smsService.sendSms(form.getPhone(), "Вы зарегестрированы!");
        mailService.sendEmailForConfirm(user.getEmail(), user.getConfirmCode());
    }
}
