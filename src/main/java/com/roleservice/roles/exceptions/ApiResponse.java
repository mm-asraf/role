package com.roleservice.roles.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApiResponse {
	
	@JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "MM-dd-yyyy hh:mm:ss")
	private LocalDateTime localDateTime;
	
	private String status;
	
	private HttpStatus error;
	
	private String message;
	
	private Long traceID;
	
	private String errorDetails;
	
	private String path; 
	
	private String exception;
	
	private Object responseObj;
	
	
}
