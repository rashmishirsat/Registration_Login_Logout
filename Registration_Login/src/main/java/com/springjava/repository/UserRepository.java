package com.springjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springjava.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
User findByEmail(String email);
}
