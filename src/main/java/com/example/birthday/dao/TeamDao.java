package com.example.birthday.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.birthday.Repository.TeamRepo;
import com.example.birthday.dto.Team;

@Repository
public class TeamDao {
	@Autowired
	private TeamRepo teamRepo;
	
	public Team saveTeam(Team team) {
        return teamRepo.save(team);
    }
	public void deleteTeamById(int team_id) {
		teamRepo.deleteById(team_id);
	}
	public List<Team> getAllTeam() {
        return teamRepo.findAll();
    }
	public Optional<Team> getTeamById(int team_id) {
		return teamRepo.findById(team_id);
	}
//	public Team addUserTeam
}
