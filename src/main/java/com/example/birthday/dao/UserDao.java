package com.example.birthday.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.birthday.Repository.UserRepo;
import com.example.birthday.dto.User;

@Repository
public class UserDao {
	@Autowired
    private UserRepo userRepository;
	public User saveUser(User user) {
        return userRepository.save(user);
    }

	public List<User> getAllUser(){
	      return userRepository.findAll();
	}


    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);   
    }
    public User getLoginAuth(String email, String password) {
        return userRepository.getLoginAuth(email,password);
    }
  public void deleteUserById(int userId)
  {
	  userRepository.deleteById(userId);
  }
  public List<User> getMemberbyTeamId(int teamId) {
	  return userRepository.getMembersByTeamId(teamId);
  }
}
