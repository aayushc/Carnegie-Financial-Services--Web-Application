package addon;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class SendMail {
	private String senderEmailID;
	private String senderPassword;
	private String emailSMTPserver;
	private String emailServerPort;
	private String receiverEmailID;
	private String emailSubject;
	private String emailBody;

	public SendMail(String receiverEmailID, String emailSubject, String emailBody){
		this.receiverEmailID = receiverEmailID;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		// change this to set sender parameters
		senderEmailID   = "cfsmaven@gmail.com"; // Sender mail
		senderPassword  = "cfsm4v3n"; // Sender mail password
		emailSMTPserver = "smtp.gmail.com"; // Mail server
		emailServerPort = "587"; // Default port
	}
	
	public void execute() {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.ssl.trust", emailSMTPserver);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);		

		try {
				Authenticator auth = new SMTPAuthenticator();
				Session session = Session.getInstance(props, auth);

				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(senderEmailID));
				message.setRecipient(Message.RecipientType.TO,new InternetAddress(receiverEmailID));
				message.setSubject(emailSubject);
				//message.setText(emailBody);
				message.setContent(emailBody, "text/html");
				
				//System.out.println("Start Sending Email to: "+receiverEmailID);
				Transport.send(message);
				//System.out.println("Email Sent to: "+receiverEmailID);
			} catch (Exception mex) {
				mex.printStackTrace();
			}

	}
	
	public class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(senderEmailID, senderPassword);
		}
	}
}