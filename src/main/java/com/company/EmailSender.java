package com.company;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private String username;
    private String password;
    private String addressTO;
    private String messageText;
    private Properties props;
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";



    public EmailSender(String username, String password, String addressTO, String messageText) {
        this.username = username;
        this.password = password;
        this.addressTO = addressTO;
        this.messageText = messageText;
    }

    public void send(){
        try{
            props = System.getProperties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.store.protocol", "pop3");
            props.put("mail.transport.protocol", "smtp");
            props.setProperty("mail.user", "ittifock@gmail.com");
            props.setProperty("mail.password", "sfamneqw123sfga");

            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {



                            return new PasswordAuthentication(username, password);
                        }});

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(addressTO,false));
            msg.setSubject("Hello");
            msg.setText(messageText);
            Transport.send(msg);
            System.out.println("Message sent.");
        }catch (MessagingException e){
            System.out.println("Error " + e);
        }
    }
}
