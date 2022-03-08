package com.TejaITB2.GlobalException;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
	

	private Date timestamp;
	private String message;
	private String errorMessage;
	public ErrorDetails(Date timestamp, String message, String errorMessage) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorMessage = errorMessage;
	}
	
	
}
