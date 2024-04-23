package com.website.mywebsite.services;

public interface MailService {
    void sendEmailForConfirm(String email, String code);
}
