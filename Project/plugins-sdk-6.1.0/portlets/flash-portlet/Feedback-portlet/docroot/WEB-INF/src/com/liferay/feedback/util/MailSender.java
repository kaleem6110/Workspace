package com.liferay.feedback.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailSender
{
	
	public static void sendEmail(String subject, String messageBody ) 
	{
		System.out
				.println("@@@@@@@@@  START MailSender.sendEmail @@@@@@@@@@@@@@@@@@@@ ");

		String host = "mail.service.emergency.lu";
	    String from = "portal-dev";
	    String pass = "QMrFlRa0O8bLXehkY2xS";
	    Properties props = System.getProperties();
	  //  props.put("mail.smtp.starttls.enable", "false"); // added this line
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "25");
	    props.put("mail.smtp.auth", "true");
	 
	    String[] to = {"kaleem.mohammed@wfp.org"}; // added this line
	    try
	    {
		    Session session = Session.getDefaultInstance(props, null);
		    MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(from));
		 
		    InternetAddress[] toAddress = new InternetAddress[to.length];
		 
		    // To get the array of addresses
		    for( int i=0; i < to.length; i++ ) { // changed from a while loop
		        toAddress[i] = new InternetAddress(to[i]);
		    }
		    System.out.println(Message.RecipientType.TO);
		 
		    for( int i=0; i < toAddress.length; i++) { // changed from a while loop
		        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		    }
		    message.setSubject( subject );
		    message.setContent( messageBody, "text/html" );
		    Transport transport = session.getTransport("smtp");
		    transport.connect(host, from, pass);
		    transport.sendMessage(message, message.getAllRecipients());
		    transport.close();
	    }
	    catch(Exception e){ e.printStackTrace(); };

		System.out
				.println("@@@@@@@@@  END MailSender.sendEmail @@@@@@@@@@@@@@@@@@@@ ");
	}

}
