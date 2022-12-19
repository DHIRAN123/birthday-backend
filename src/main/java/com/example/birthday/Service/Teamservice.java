package com.example.birthday.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.birthday.dao.TeamDao;
import com.example.birthday.dao.UserDao;
import com.example.birthday.dto.Team;
import com.example.birthday.dto.User;
import com.example.birthday.dto.Team;
import com.example.birthday.dto.Team;
import com.example.birthday.util.ResponseStructure;
@Service
public class Teamservice {
	
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private UserDao userDao;
	
	 public ResponseEntity<ResponseStructure<Team>> createTeam(Team team) {
         Team createdTeam =  teamDao.saveTeam(team);
         ResponseStructure<Team> responseStructure = new ResponseStructure<>();
         responseStructure.setError(false);
         responseStructure.setMessage("Team created");
         responseStructure.setData(createdTeam);
         return new ResponseEntity<ResponseStructure<Team>>(responseStructure, HttpStatus.CREATED);
}
	 
		public  ResponseEntity<ResponseStructure<List<Team>>> getAllTeam() {
	        List<Team> teams = teamDao.getAllTeam();
	        ResponseStructure<List<Team>> responseStructure = new ResponseStructure<>();
	        responseStructure.setData(teams);
	        if (teams.isEmpty()) {
	            responseStructure.setError(true);
	            responseStructure.setMessage("no team found");
	            return new ResponseEntity<ResponseStructure<List<Team>>> (responseStructure, HttpStatus.NO_CONTENT);
	        }
	        else {
	        responseStructure.setError(false);
	        responseStructure.setMessage("list of all team");
	        return new ResponseEntity<ResponseStructure<List<Team>>> (responseStructure, HttpStatus.OK);
	        }
	    }
		public ResponseEntity<ResponseStructure<Team>> getTeamById(int teamid) {
	        Optional<Team> found=teamDao.getTeamById(teamid);
	        ResponseStructure<Team> responseStructure = new ResponseStructure<>();
	        if(found.isPresent()) {
	            responseStructure.setError(false);
	            responseStructure.setMessage("data found");
	            responseStructure.setData(found.get());
	            return new ResponseEntity<ResponseStructure<Team>>(responseStructure, HttpStatus.OK);
	        }
	        else {
	            responseStructure.setError(true);
	            responseStructure.setMessage("not found");
	            responseStructure.setData(null);

	            return new ResponseEntity<ResponseStructure<Team>>(responseStructure, HttpStatus.NO_CONTENT);
	        }
	    }
		public  ResponseEntity<ResponseStructure<String>> deleteTeamById(int teamId) {
	        ResponseStructure<String> responseStructure = new ResponseStructure<>();
	        teamDao.deleteTeamById(teamId); 
	        responseStructure.setError(false);
	        responseStructure.setMessage("team deleted");
	        responseStructure.setData("Team with "+ teamId+" deleted");
	        return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);            

	    }
		public ResponseEntity<ResponseStructure<User>> addUserToTeam(int teamid, int userid) {
	        Team existingTeam = teamDao.getTeamById(teamid).get();
	        Optional<User> found = userDao.getUserById(userid);
	        ResponseStructure<User> responseStructure = new ResponseStructure<>();
	        if (found.isPresent() && existingTeam!=null) {
	            User existingUser = found.get();
	            existingUser.setTeam(existingTeam);

	 

	            responseStructure.setError(false);
	            responseStructure.setMessage("user added to team");
	            responseStructure.setData(userDao.saveUser(existingUser));
	            return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
	        }
	        else {
	            responseStructure.setError(false);
	            responseStructure.setMessage("no user found");
	            responseStructure.setData(null);
	            return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NO_CONTENT);            
	        }
	    }
		public ResponseEntity<ResponseStructure<User>> removeUserFromTeam(int teamid, int userid) {
			 Team existingTeam = teamDao.getTeamById(teamid).get();
		     Optional<User> found = userDao.getUserById(userid);
	        ResponseStructure<User> responseStructure = new ResponseStructure<>();
	        if (found.isPresent() && existingTeam!=null) {
	            User existingUser = found.get();
	            existingUser.setTeam(null);

	 

	            responseStructure.setError(false);
	            responseStructure.setMessage("user removed from team");
	            responseStructure.setData(userDao.saveUser(existingUser));
	            return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
	        }
	        else {
	            responseStructure.setError(false);
	            responseStructure.setMessage("no user found");
	            responseStructure.setData(null);
	            return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NO_CONTENT);            
	        }
	    }
}
