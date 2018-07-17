package com.team1.demo.Services;

import com.team1.demo.Entity.Hotel;
import com.team1.demo.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    private HotelRepository hotelRepository;

    @Autowired
    public void setHotelRepository(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }


    public Hotel findOne(Long id) {
        return hotelRepository.findOne(id);
    }


    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }


    public void delete(Long id) {
        hotelRepository.delete(id);
    }
}
