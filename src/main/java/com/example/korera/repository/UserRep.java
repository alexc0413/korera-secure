package com.example.korera.repository;

import com.example.korera.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRep extends JpaRepository<User,String >{
}
