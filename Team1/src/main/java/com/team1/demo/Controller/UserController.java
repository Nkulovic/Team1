package com.team1.demo.Controller;


import com.team1.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setHotelService(UserService userService) {
        this.userService = userService;
    }
    // requestmapping
    
}
