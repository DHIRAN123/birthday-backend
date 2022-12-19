package com.example.birthday.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.birthday.dto.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	  @Query("SELECT u FROM User u WHERE email = :email AND password = :password")
	    User getLoginAuth(@Param("email") String email,@Param("password") String password);
	  @Query("SELECT i FROM User i WHERE team_id = :team_id")
	  List<User> getMembersByTeamId(@Param("team_id") int team_id);
}
