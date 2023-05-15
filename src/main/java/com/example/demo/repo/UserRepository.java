package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer>{

	Optional<UserInfo> findByUsername(String username);
}
