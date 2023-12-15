package com.example.imagehacker.service;

import com.example.imagehacker.Logger;
import com.example.imagehacker.dao.URLRepository;
import com.example.imagehacker.dao.UserRepository;
import com.example.imagehacker.entity.Account;
import com.example.imagehacker.entity.AccountUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

/**
 * UserService.
 *
 * @author Nguyễn Hải
 * Created 27/11/2023
 */

@Service
public class UserService {

    private final UserRepository userRepository;
    private final URLRepository urlRepository;

    @Autowired
    public UserService(UserRepository userRepository, URLRepository urlRepository) {
        this.userRepository = userRepository;
        this.urlRepository = urlRepository;
    }

    public List<Account> findAll() {
        return userRepository.findAll();
    }

    public Account findByUsername(String username, String pass) {
        return userRepository.findByUsernameAndPassword(username,pass);
    }

    public void save(Account user) {
        userRepository.save(user);
    }

    public Account authenticateUser(String username, String rawPassword) {
        String hashedPassword = md5(rawPassword);
        Account user = userRepository.findByUsernameAndPassword(username,hashedPassword);
        if (user != null) {
                return user;
            }
        return null;
    }

    public AccountUrl checkaccountUrl(Account account) {
        AccountUrl user = urlRepository.findAcc(account);
            return user;
    }

    public String checkUrl(Account account){
        String url = urlRepository.findLink(account.userId);
        return url;
    }

    public String getIP(Account account){
        String ip = urlRepository.findIP(account.userId);
        return ip;
    }

    public int sumIP(String ip_add){
        int ip = urlRepository.sumViewsByIpAddress(ip_add);
        return ip;
    }

    public static String md5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            Logger.error("Lỗi mã hóa password");
        }
        return "";
    }

}
