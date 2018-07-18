package com.team1.demo.Controller;

import com.team1.demo.Entity.Reservation;
import com.team1.demo.Entity.Users;
import com.team1.demo.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReservationController {
    private ReservationService reservationService;


    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    public String reservationView(@Valid Reservation reservation, BindingResult bindingResult) {
        System.out.println("prije ifa");
//        System.out.println(reservation.getUser().getUserID());
        if(bindingResult.hasErrors()) {
            //return "redirect:/userpanel";
            System.out.println("============================= greska");

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
        }
        else
        {

            reservationService.save(reservation);
            System.out.println("nije greska");
            return "redirect:/reservation" ;
        }
        return "";
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public String reservationResult() {
        return "views/reservation";
    }
}

