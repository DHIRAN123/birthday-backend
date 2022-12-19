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

import com.example.birthday.Service.Teamservice;
import com.example.birthday.dto.Team;
import com.example.birthday.dto.User;
import com.example.birthday.util.ResponseStructure;

@RestController
@CrossOrigin
public class TeamController {
	@Autowired
	private Teamservice teamService;
    @PostMapping(value = "/team")
    public ResponseEntity<ResponseStructure<Team>> saveTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    //this is for our need
    @GetMapping(value = "/team")
    public ResponseEntity<ResponseStructure<List<Team>>> getAllTeam() {
        return teamService.getAllTeam();
    }


    @GetMapping(value = "/team/{id}")
    public ResponseEntity<ResponseStructure<Team>> getTeamById(@PathVariable int id) {
        return teamService.getTeamById(id);
    }

    @DeleteMapping(value = "/team/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteTeam(@PathVariable int id) {
        return teamService.deleteTeamById(id);
    }
    @GetMapping("/addUserToTeam/{teamid}/{userid}")
    public ResponseEntity<ResponseStructure<User>> addUserToTeam(@PathVariable int teamid, @PathVariable int userid) {
        return teamService.addUserToTeam(teamid, userid);
    }
    @GetMapping("/removeUserFromTeam/{teamid}/{userid}")
    public ResponseEntity<ResponseStructure<User>> removeUserToTeam(@PathVariable int teamid, @PathVariable int userid) {
        return teamService.removeUserFromTeam(teamid, userid);
    }
}
