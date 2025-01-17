package com.basic.security;

public interface UserService {

    public User registerUser(String username, String plainPassword);

    public boolean validateLogin(String username, String plainPassword);
}
