package com.example.birthday.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.birthday.dto.Team;

public interface TeamRepo extends JpaRepository<Team,Integer>{

}
