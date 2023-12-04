package com.uph.simplecaptcha.services;

import com.uph.simplecaptcha.dto.UserDTO;
import com.uph.simplecaptcha.entities.UserEntity;
import com.uph.simplecaptcha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserServices {
    static Cipher cipher;

    @Autowired
    UserRepository userRepository;


    @Override
    public UserEntity registerUser(UserEntity user) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(encryptValue(user.getPassword()));
        userEntity.setId(user.getId());
        userEntity.setRole(user.getRole());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(userEntity);
    }

    @Override
    public UserDTO getUserDetail(String idUser) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        UserEntity userEntity = userRepository.findById(idUser).get();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setEmail(encryptValue(userEntity.getEmail()));
        userDTO.setPhoneNumber(encryptValue(userEntity.getPhoneNumber()));
        return userDTO;
    }

    private String encryptValue(String password)
            throws NoSuchAlgorithmException,
            NoSuchPaddingException,
            IllegalBlockSizeException,
            BadPaddingException,
            InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        cipher = Cipher.getInstance("AES");
        return encrypt(password, secretKey);
    }

    private static String encrypt(String plainText, SecretKey secretKey)
            throws InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(encryptedByte);
    }

    private static String decrypt(String encryptedText, SecretKey secretKey)
            throws IllegalBlockSizeException,
            BadPaddingException,
            InvalidKeyException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptByte = cipher.doFinal(encryptedTextByte);
        return new String(decryptByte);
    }
}
