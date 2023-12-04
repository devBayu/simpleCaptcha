package com.uph.simplecaptcha.services;

import com.uph.simplecaptcha.dto.UserDTO;
import com.uph.simplecaptcha.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public interface UserServices {
    UserEntity registerUser(UserEntity user) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
    UserDTO getUserDetail(String id) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
}
