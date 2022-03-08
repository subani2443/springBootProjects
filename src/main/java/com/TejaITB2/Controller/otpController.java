package com.TejaITB2.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TejaITB2.Model.OtpModel;
import com.TejaITB2.Service.OtpService;

@RestController
public class otpController {

	@Autowired
	OtpService service;

	private SimpMessagingTemplate webSocket;

	private final String TOPIC_DESTINATION = "/lesson/sms";

	@PostMapping("/mobileNo")
	public ResponseEntity<String> smsSubmit(@RequestBody OtpModel sms) {
		try {
			System.out.println("hello");
			service.send(sms);
			System.out.println(sms);
//			System.out.println("hello");
		} catch (Exception e) {

			return new ResponseEntity<String>("somethingProblem", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("OTP send successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/smscallback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void smsCallback(@RequestBody MultiValueMap<String, String> map) {
		service.receive(map);
		webSocket.convertAndSend(TOPIC_DESTINATION,
				getTimeStamp() + ": Twilio has made a callback request! Here are the contents: " + map.toString());
	}

	private String getTimeStamp() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	}

}