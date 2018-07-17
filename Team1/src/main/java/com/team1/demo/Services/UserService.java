package com.team1.demo.Services;

import com.team1.demo.Entity.Hotel;
import com.team1.demo.Entity.Users;
import com.team1.demo.Repository.HotelRepository;
import com.team1.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Iterable<Users> findAll() {
        return userRepository.findAll();
    }


    public Users findOne(Long id) {
        return userRepository.findOne(id);
    }


    public Users save(Users user) {
        return userRepository.save(user);
    }


    public void delete(Long id) {
        userRepository.delete(id);
    }
}
