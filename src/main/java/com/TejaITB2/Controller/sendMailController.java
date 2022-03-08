package com.TejaITB2.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sendMailController {
	
	@RequestMapping(value = "/sendemail")
	public String sendEmail(HttpServletResponse response) throws AddressException, MessagingException, IOException  {
		sendmail();
		return "Email sent successfully";
	}

	private void sendmail() throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("subanishaik2443@gmail.com","babuaish1");
				
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("subanishaik2443@gmail.com", true));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("imreddy97@gmail.com"));
		msg.setSubject("TEJAIT  email sending with springBoot");
		msg.setContent("TEJAIT email", "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("TEJAIT email", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();

		attachPart.attachFile("D:\\files.txt");
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);
	}
}






























//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//@SpringBootApplication
//public class SmsdemoApplication implements ApplicationRunner {
//   private final static String ACCOUNT_SID = "AC588bbdae7a1b7ddfbbb0ecfaf53617c0";
//   private final static String AUTH_ID = "8001ce5a8c35cf06c4a45a7022427512";
//
//   static {
//      Twilio.init(ACCOUNT_SID, AUTH_ID);
//   }
//   public static void main(String[] args) {
//      SpringApplication.run(SmsdemoApplication.class, args);
//   }
//   @Override
//   public void run(ApplicationArguments arg0) throws Exception {
//      Message.creator(new PhoneNumber("8886787820"), new PhoneNumber("8886787820"),
//         "Message from Spring Boot Application").create();
//   }
//}

