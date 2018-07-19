package com.team1.demo.Controller;

import com.team1.demo.Entity.Hotel;
import com.team1.demo.Entity.Users;
import com.team1.demo.Services.HotelService;
import com.team1.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

@Controller
public class AdminController {
    private UserService userService;
    private HotelService hotelService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(UserService userService, HotelService hotelService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userService = userService;
        this.hotelService = hotelService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String addUser(@Valid Users user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            //return "redirect:/userpanel";
        }
        else
        {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.save(user);
            return "redirect:/adduser" ;
        }
        return "";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String userAddedResult() {
        return "views/useraddedview";
    }

    @RequestMapping(value = "/addhotel", method = RequestMethod.POST)
    public String addHotel(@Valid Hotel hotel, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            //return "redirect:/userpanel";
        }
        else
        {
            hotelService.save(hotel);
            return "redirect:/addhotel" ;
        }
        return "";
    }

    @RequestMapping(value = "/addhotel", method = RequestMethod.GET)
    public String hotelAddedResult() {
        return "views/hoteladdedview";
    }

    @RequestMapping(value = "/userslist", method = RequestMethod.GET)
    public String listUsers(Model model) {
        Iterable<Users> userslist = userService.findAll();
        model.addAttribute("users", userslist);
        return "views/userslist";
    }

    @RequestMapping(value = "/edit/user/{id}")
    public String editUser(Model model , @PathVariable("id") String id) {
        //Users user = userService.findById(Long.parseLong(id));
        Users user = userService.getOne(Long.parseLong(id));

        model.addAttribute("user", user);
       // userService.delete(user);
        return "views/editprofile";

    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUserProfile(@Valid Users user, BindingResult bindingResult) {
        //Users toDelete = userService.findById(user.getUserID());
        System.out.println("prijeee");
        if(bindingResult.hasErrors()) {
            System.out.println("greska");
        }
        else {
            System.out.println("post save");
            userService.save(user);
            return "redirect:/views/admin";
        }

        return "";
    }

    @RequestMapping(value = "/deleteuser/{id}")
    public String deleteUser(Model model , @PathVariable("id") String id) {
        //Users user = userService.findById(Long.parseLong(id));
        //Users user = userService.getOne(Long.parseLong(id));

        // ne moze zbog constrainta
        userService.deleteById(Long.parseLong(id));

        return "views/useraddedview";

    }
    /*@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
    public String editResult() {
        //Users user = new Users();

        return "views/editprofile";
    }*/

    @RequestMapping(value = "/edit/hotel/{id}")
    public String editHotel(Model model , @PathVariable("id") String id) {
        //Users user = userService.findById(Long.parseLong(id));
        Hotel hotel = hotelService.getOne(Long.parseLong(id));

        model.addAttribute("hotel", hotel);
        // userService.delete(user);
        return "views/edithotel";

    }
    @RequestMapping(value = "/edithotel", method = RequestMethod.POST)
    public String editHotelInfo(@Valid Hotel hotel, BindingResult bindingResult) {
        //Users toDelete = userService.findById(user.getUserID());
        System.out.println("prijeee");

        if(bindingResult.hasErrors()) {
            System.out.println("greska");
        }
        else {
            System.out.println("post save");
            hotelService.save(hotel);
            return "redirect:/views/admin";
        }

        return "";
    }
}
