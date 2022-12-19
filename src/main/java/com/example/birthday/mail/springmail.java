package com.example.birthday.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.birthday.dao.UserDao;
import com.example.birthday.dto.User;

@Service
public class springmail {
	@Autowired
	private UserDao userDao;

	
    private static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

 

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

 

            titleCase.append(c);
        }

 

        return titleCase.toString();
    }

    private void sendEmail(String to, String subject, String message) {
        String from = "dhireen483@gmail.com";
        String password = "lsxokthhdysdwijd";
        //variable for host
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        System.out.println("Properties" + properties);

        //setting imp. properties
        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // step:1 get the session
        Session session = Session.getInstance(properties, new Authenticator() {

 

            @Override
            protected PasswordAuthentication getPasswordAuthentication() { 
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true);

        // step 2: compose the message
        MimeMessage m = new MimeMessage(session);
        try {
            //from
            m.setFrom(from);    

            // to
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //subject
            m.setSubject(subject);

            //message
            m.setContent(message, "text/html");

            //step:3 send the message using transport class
            Transport.send(m);
            System.out.println("sent success");


        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public  void sendGreet(int userid){
       User user=userDao.getUserById(userid).get();
       
        String to = user.getEmail();
        String subject = "Happy Birthday";

        String message = String.format("<h1>Happy birthday To You May God Bless You</h1>");
                

        this.sendEmail(to, subject, message);
    }

}
