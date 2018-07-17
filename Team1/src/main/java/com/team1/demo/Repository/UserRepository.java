package com.team1.demo.Repository;

import com.team1.demo.Entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<Users, Long> {

   // Users findOne(Long id);

    Users findByUsername(String username);

    @Query("SELECT DISTINCT u.role from Users u")
    Set<String> findDistinctRole();
}