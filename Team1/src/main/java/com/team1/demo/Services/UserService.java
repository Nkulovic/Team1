package com.team1.demo.Services;

import com.team1.demo.Entity.Users;
import com.team1.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<Users> findAll() {
        return userRepository.findAll();
    }


    //public Users findOne(Long id) {
     //   return userRepository.findOne(id);
   // }


    public void save(Users user) {
         userRepository.save(user);
    }


    public void delete(Users user ) {
        userRepository.delete(user);
    }

    public Users findById(Long id) {
        return userRepository.findByUserID(id);
    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public Users getOne(Long id) {
        return userRepository.getOne(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
