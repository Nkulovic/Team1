package com.team1.demo.Services;

import com.team1.demo.Entity.Hotel;
import com.team1.demo.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;


    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public void save(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    public Hotel getOne(Long id) {
        return hotelRepository.getOne(id);
    }

    public void updateHotel(String name, String descritpion, String location, double longitude, double latitude, Long id){
        hotelRepository.updateHotel(name, descritpion, location, longitude, latitude, id);
    }
}
