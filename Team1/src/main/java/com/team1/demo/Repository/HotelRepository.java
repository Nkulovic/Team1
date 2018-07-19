package com.team1.demo.Repository;

import com.team1.demo.Entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// komunicira s bazom
@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    Hotel getOne(Long id);
//    Hotel findOne(Long id);

    //Hotel findByName(String name);
}
