package com.goodee.diary.demo.user.service;

import com.goodee.diary.demo.SHA2;
import com.goodee.diary.demo.spending.domain.Spending;
import com.goodee.diary.demo.user.dao.UserRepository;
import com.goodee.diary.demo.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        list = userRepository.findAll();
        return list;
    }

    @Transactional
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User savePW(String useremail, String username, String password) {
        String pw = SHA2.getSHA256(password);
        List<User> findUser = this.getAll();
        User saveduser = null;
        for (User u :
                findUser) {
            if (u.getUseremail().equals(useremail) && u.getUsername().equals(username)) {
                User user = u;
                user.setUsername(username);
                user.setUseremail(useremail);
                user.setUserpw(pw);
                saveduser = userRepository.save(user);
                System.out.println("savedUser method : " + saveduser);
                break;
            }
        }
        return saveduser;
    }
}
