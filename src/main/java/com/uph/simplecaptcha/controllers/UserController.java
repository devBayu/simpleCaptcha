package com.uph.simplecaptcha.controllers;

import com.uph.simplecaptcha.dto.UserDTO;
import com.uph.simplecaptcha.entities.UserEntity;
import com.uph.simplecaptcha.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/user")
    public UserEntity registerUser(@RequestBody UserEntity user) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return userServices.registerUser(user);
    }

    @PostMapping("/user/detail/{id}")
    public UserDTO getUserDetail(@PathVariable String id) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return userServices.getUserDetail(id);
    }
}
