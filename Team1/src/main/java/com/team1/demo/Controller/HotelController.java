package com.team1.demo.Controller;

import com.team1.demo.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HotelController {
    private HotelService hotelService;
    /*
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    * */

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }
}
