package com.team1.demo.Controller;


import com.team1.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String redirectToPanel(Authentication authentication) {
        if (authentication.getAuthorities().toString().contains("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        else if (authentication.getAuthorities().toString().contains("ROLE_USER")) {
            return "redirect:/userpanel";
        }
        else if (authentication.getAuthorities().toString().contains("ROLE_SUPERVISOR")) {
         //   System.out.println("super");
            return "redirect:/supervisor";
        }
        return "/login";
    }

    @RequestMapping(value = "/login")
    public String showLoginView() {
        //System.out.println("ugh");
        return "auth/login";
    }

    @RequestMapping(value = "/userpanel")
    public String showUserPanelView() {
        //System.out.println("ugh");
        return "views/userpanel";
    }




}
