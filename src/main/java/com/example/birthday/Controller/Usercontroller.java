package com.example.birthday.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.birthday.Service.Userservice;
import com.example.birthday.dto.User;
import com.example.birthday.util.ResponseStructure;

@RestController
@CrossOrigin
public class Usercontroller {
	@Autowired
    private Userservice userService;

    @PostMapping(value = "/user")
    public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //this is for our need
    @GetMapping(value = "/user")
    public ResponseEntity<ResponseStructure<List<User>>> getAllUser() {
        return userService.getAllUser();
    }


    @GetMapping(value = "/user/{id}")
    public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int id) {
        return userService.deleteUserById(id);
    }

    //login authentication
    @PostMapping(value = "/login")
    public ResponseEntity<ResponseStructure<User>> loginAuth(@RequestBody User user) {
        return userService.loginAuth(user);
    }
    @GetMapping(value="/userbyteamid/{id}")
    public ResponseEntity<ResponseStructure<List<User>>> getuserbyteamid(@PathVariable int id) {
    	return userService.getUserByTeamId(id);
    }
    
}
