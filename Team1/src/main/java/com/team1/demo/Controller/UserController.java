package com.team1.demo.Controller;


import com.team1.demo.Entity.Hotel;
import com.team1.demo.Entity.Reservation;
import com.team1.demo.Entity.Users;
import com.team1.demo.Services.HotelService;
import com.team1.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private HotelService hotelService;

    @Autowired
    public UserController(UserService userService, HotelService hotelService) {

        this.userService = userService;
        this.hotelService = hotelService;
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String redirectToPanel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        //System.out.println(name);
        if (authentication.getAuthorities().toString().contains("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        else if (authentication.getAuthorities().toString().contains("ROLE_USER")) {
           // authentication.getPrincipal().toString();

            Users user = userService.findByUsername(name);

            if(user == null) {
                //
            }
            return "redirect:/userpanel/" + user.getUserID();
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

    @RequestMapping(value = "/admin")
    public String showAdminPanelView() {
        //System.out.println("ugh");
        return "views/admin";
    }

    @RequestMapping(value = "/userpanel")
    public String showUserPanelView() {
        //System.out.println("ugh");
        return "views/userpanel";
    }

    @RequestMapping(value = "/supervisor")
    public String showSupervisorPanelView(){
        return "views/supervisor";
    }

    @RequestMapping(value = "/userpanel/{id}")
    public String showUserPanel(Model model, @PathVariable("id") String id) {
        Users user = userService.findById(Long.parseLong(id));
        System.out.println("userid");
        System.out.println(user.getUserID());
        if(user == null) {
            System.out.println("user is null");
            return "redirect:/?error=user-is-null";
        }
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        Iterable<Hotel> hotels = hotelService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("hotels", hotels);
        model.addAttribute("reservation", reservation);
        // pakuje varijable
        return "views/userpanel";
    }
}
