package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Map;
import java.util.Properties;

@RestController
public class server
{
    @RequestMapping("/sendMail")
    @CrossOrigin(origins = "http://34.93.251.164:7777/sendMail")
    public String sendMail(@RequestBody Map<String, Object> payload)
    {
        String to=(String)payload.get("to");
        String mailMessage=(String)payload.get("message");



        final String myMail = "********";
        final String password = "**********";
        String fromEmail = "***********";
        String toEmail = to;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(myMail,password);
            }
        });
        //Start our mail message
        MimeMessage message = new MimeMessage(session);
        boolean bool1=false;
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Reply from Lovely Professional University");

            Multipart emailContent = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(mailMessage);


            emailContent.addBodyPart(textBodyPart);

            message.setContent(emailContent);

            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println(e);
            bool1=true;
        }
        if(!bool1)
        {
            return "OK";
        }
        else
        {
            return "Went Wrong";
        }
    }
}
