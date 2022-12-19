package com.example.birthday.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.birthday.dto.User;
import com.example.birthday.mail.springmail;

@RestController
@CrossOrigin
public class MailController {
@Autowired
private springmail mail;

@GetMapping("/sendmail/{userid}")
public List<User> sendMail(@PathVariable int userid) {
    mail.sendGreet(userid);
    return null;
}
}
