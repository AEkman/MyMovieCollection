package com.newton.mymoviecollection.repository;

import com.newton.mymoviecollection.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUsername(String username);
}