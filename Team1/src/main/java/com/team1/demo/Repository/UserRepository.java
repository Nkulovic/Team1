package com.team1.demo.Repository;

import com.team1.demo.Entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {

    Users findOne(Long id);

    void delete(Long id);
}