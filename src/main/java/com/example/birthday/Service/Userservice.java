package com.example.birthday.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.birthday.dao.UserDao;
import com.example.birthday.dto.User;
import com.example.birthday.util.ResponseStructure;

@Service
public class Userservice {
	@Autowired
	private UserDao userDao;
	@SuppressWarnings("unused")
    public ResponseEntity<ResponseStructure<User>> createUser(User user) {
            User createdUser =  userDao.saveUser(user);
            ResponseStructure<User> responseStructure = new ResponseStructure<>();
            responseStructure.setError(false);
            responseStructure.setMessage("Employee created");
            responseStructure.setData(createdUser);
            return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.CREATED);
 }
	public  ResponseEntity<ResponseStructure<List<User>>> getAllUser() {
        List<User> users = userDao.getAllUser();
        ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
        responseStructure.setData(users);
        if (users.isEmpty()) {
            responseStructure.setError(true);
            responseStructure.setMessage("no user found");
            return new ResponseEntity<ResponseStructure<List<User>>> (responseStructure, HttpStatus.NO_CONTENT);
        }
        else {
        responseStructure.setError(false);
        responseStructure.setMessage("list of all user");
        return new ResponseEntity<ResponseStructure<List<User>>> (responseStructure, HttpStatus.OK);
        }
    }
	public ResponseEntity<ResponseStructure<User>> getUserById(int userid) {
        Optional<User> found=userDao.getUserById(userid);
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        if(found.isPresent()) {
            responseStructure.setError(false);
            responseStructure.setMessage("data found");
            responseStructure.setData(found.get());
            return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
        }
        else {
            responseStructure.setError(true);
            responseStructure.setMessage("not found");
            responseStructure.setData(null);

            return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NO_CONTENT);
        }
    }
	public  ResponseEntity<ResponseStructure<String>> deleteUserById(int userId) {
        ResponseStructure<String> responseStructure = new ResponseStructure<>();
        userDao.deleteUserById(userId); 
        responseStructure.setError(false);
        responseStructure.setMessage("user deleted");
        responseStructure.setData("User with "+ userId+" deleted");
        return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);            

    }
	public ResponseEntity<ResponseStructure<User>> loginAuth(User user){
        User checkuser = userDao.getLoginAuth(user.getEmail(), user.getPassword());
        if (checkuser == null) {
            ResponseStructure<User> responseStructure = new ResponseStructure<>();
            responseStructure.setError(true);
            responseStructure.setMessage("no user found");
            responseStructure.setData(checkuser);

            return new ResponseEntity<ResponseStructure<User>> (responseStructure, HttpStatus.NOT_FOUND);
        }
        else {
            ResponseStructure<User> responseStructure = new ResponseStructure<>();
            responseStructure.setError(false);
            responseStructure.setMessage("user found");
            responseStructure.setData(checkuser);
            return new ResponseEntity<ResponseStructure<User>> (responseStructure, HttpStatus.OK);
        }
	
	}
	public ResponseEntity<ResponseStructure<List<User>>> getUserByTeamId(int teamid) {

        List<User> users = userDao.getMemberbyTeamId(teamid);

       

        if (users.isEmpty()) {

            ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();

            responseStructure.setError(true);

            responseStructure.setMessage("no users link with this team");

            responseStructure.setData(users);

           

            return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.OK);

        }

        else {

            ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();

            responseStructure.setError(false);

            responseStructure.setMessage("list of User");

            responseStructure.setData(users);

           

            return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.OK);

        }

    }
}


