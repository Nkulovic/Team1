package com.team1.demo.Controller;

import com.team1.demo.Entity.Hotel;
import com.team1.demo.Entity.Reservation;
import com.team1.demo.Entity.Users;
import com.team1.demo.Services.HotelService;
import com.team1.demo.Services.ReservationService;
import com.team1.demo.Services.UserService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {
    private UserService userService;
    private HotelService hotelService;
    private ReservationService reservationService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(UserService userService, HotelService hotelService, ReservationService reservationService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userService = userService;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/adduserpage")
    public String showAddUserPage(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        return "views/adduserpage";
    }

    @RequestMapping(value = "/addhotelpage")
    public String showAddHotelPage(Model model) {
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        return "views/addhotelpage";
    }

    //  LISTS
//  shows a page with list of users
    @RequestMapping(value = "/userslistpage")
    public String showUsersListPage(Model model) {
        Iterable<Users> usersList = userService.findAll();
        model.addAttribute("usersList", usersList);
        return "views/userslistpage";
    }

    //  shows a page with list of hotels
    @RequestMapping(value = "/hotelslistpage")
    public String showHotelsListPage(Model model) {
        Iterable<Hotel> hotelsList = hotelService.findAll();
        model.addAttribute("hotelsList", hotelsList);
        return "views/hotelslistpage";
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
          Users user = userService.findById(Long.parseLong(id));
     //     System.out.println(user.getUserID());
          model.addAttribute("user", user);
        //System.out.println("post pakovanje " + user.getUserID() + " " + user.getFirstName());
       // userService.delete(user);
        return "views/editprofile";

    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUserProfile(@Valid Users user, BindingResult bindingResult) {

       // System.out.println("prijeee");
        if(bindingResult.hasErrors()) {
        //    System.out.println("greska");
        }
        else {
       //     System.out.println("post save " + user.getUsername());
            userService.updateUser(user.getFirstName(), user.getLastName(), user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()), user.getLongitude(), user.getLatitude(), user.getRole(), user.getUserID());
            return "redirect:/admin";
        }

        return "";
    }

    @RequestMapping(value = "/deleteuser/{id}")
    public String deleteUser(Model model , @PathVariable("id") String id) {
        Users user = userService.findById(Long.parseLong(id));
        List<Reservation> reservations = (List<Reservation>) reservationService.findByUser(user);
        for (Reservation reservation : reservations) {
            reservationService.delete(reservation);
        }
        //userService.userDeleted(Long.parseLong(id));
        userService.delete(user);
        return "views/useraddedview";
    }


    @RequestMapping(value = "/deletehotel/{id}")
    public String deleteHotel(@PathVariable("id") String id) {
        Hotel hotel = hotelService.findByHotelId(Long.parseLong(id));
        List<Reservation> reservations = (List<Reservation>) reservationService.findByHotel(hotel);
        for (Reservation reservation: reservations) {
            reservationService.delete(reservation);
        }
        hotelService.delete(hotel);
        return "views/hoteladdedview";
    }

   @RequestMapping(value = "/gethotel/{id}")
    @ResponseBody
    public Hotel getHotelInfo(@PathVariable("id") String id) {
        Hotel hotel = hotelService.findByHotelId(Long.parseLong(id));
        if(hotel != null) {
            return hotel;
        }
        else {
            return new Hotel();
        }

    }

    //updatelonglat
    @RequestMapping(value = "/updatelonglat/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String getTest(@PathVariable("id") String id, @RequestBody Object data) {
        Users user = userService.findById(Long.parseLong(id));
        //double longitude = Double.parseDouble(data)
        System.out.println(data);
        return "";
    }
    @RequestMapping(value = "/edit/hotel/{id}")
    public String editHotel(Model model , @PathVariable("id") String id) {
        Hotel hotel = hotelService.getOne(Long.parseLong(id));
        model.addAttribute("hotel", hotel);
        return "views/edithotel";

    }
    @RequestMapping(value = "/edithotel", method = RequestMethod.POST)
    public String editHotelInfo(@Valid Hotel hotel, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // error
        }
        else {

            hotelService.updateHotel(hotel.getName(), hotel.getDescription(), hotel.getLocation(), hotel.getLongitude(), hotel.getLatitude(), hotel.getHotelID());
            return "redirect:/admin";
        }

        return "";
    }
}
