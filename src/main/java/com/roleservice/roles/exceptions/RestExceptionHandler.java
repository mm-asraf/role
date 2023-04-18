package com.roleservice.roles.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	
	

	@ExceptionHandler(BussinessNotFound.class)
	ResponseEntity<ApiResponse> handleBussinessEntityNotFound(BussinessNotFound bex,WebRequest wb,Exception e) {
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		
		String stackTrace = stringWriter.toString();
		
		ApiResponse apiResponse =   ApiResponse.builder()
									.status(String.valueOf(HttpStatus.NOT_FOUND.value()))
									.error(HttpStatus.NOT_FOUND)
									.localDateTime(LocalDateTime.now())
									.path(wb.getDescription(false))
									.message(bex.getMessage())
									.exception(bex.getLocalizedMessage())
									.traceID(Instant.now().toEpochMilli())
									.errorDetails(stackTrace)
									.build();
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@ExceptionHandler(RoleNotFound.class)
	ResponseEntity<ApiResponse> handleRoleEntityNotFound(RoleNotFound rex,WebRequest wb,Exception e) {
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace();
		
		String stackTrace = stringWriter.toString();
		
		ApiResponse apiResponse = ApiResponse.builder()
								  .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
								  .error(HttpStatus.NOT_FOUND)
								  .localDateTime(LocalDateTime.now())
								  .path(wb.getDescription(false))
								  .message(rex.getMessage())
								  .exception(rex.getLocalizedMessage())
								  .traceID(Instant.now().toEpochMilli())
								  .errorDetails(stackTrace)
								  .build();
		return ResponseEntity.ok(apiResponse);
	}
	
	
	@ExceptionHandler(UserNotFound.class)
	ResponseEntity<ApiResponse> handleUserNotFoundEntity(UserNotFound uf,WebRequest wb,Exception e) {
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace();
		
		String stackTrace = stringWriter.toString();
		
		ApiResponse apiResponse = ApiResponse.builder()
								.status(String.valueOf(HttpStatus.NOT_FOUND.value()))
								.error(HttpStatus.NOT_FOUND)
								.localDateTime(LocalDateTime.now())
								.path(wb.getDescription(false))
								.message(uf.getMessage())
								.exception(uf.getLocalizedMessage())
								.traceID(Instant.now().toEpochMilli())
								.errorDetails(stackTrace)
								.build();
				
		
		return ResponseEntity.ok(apiResponse);
	}
	
//	@ExceptionHandler(NoHandlerFoundException.class)
//	ResponseEntity<ApiResponse> handleNoHandlerFoundException(NoHandlerFoundException ex,WebRequest wb) {
//		StringWriter stringWriter = new StringWriter();
//		PrintWriter printWriter = new PrintWriter(stringWriter);
//		ex.printStackTrace();
//		
//		String stackTrace = stringWriter.toString();
//		
//		ApiResponse apiResponse = ApiResponse.builder()
//								.status(String.valueOf(HttpStatus.NOT_FOUND.value()))
//								.error(HttpStatus.NOT_FOUND)
//								.localDateTime(LocalDateTime.now())
//								.path(wb.getDescription(false))
//								.message(ex.getMessage())
//								.exception(ex.getLocalizedMessage())
//								.traceID(Instant.now().toEpochMilli())
//								.errorDetails(stackTrace)
//								.build();
//				
//		
//		return ResponseEntity.ok(apiResponse);
//		
//	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<ApiResponse> handleAnyTypeFoundException(Exception ex,WebRequest wb) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		ex.printStackTrace();
		
		String stackTrace = stringWriter.toString();
		
		ApiResponse apiResponse = ApiResponse.builder()
								.status(String.valueOf(HttpStatus.NOT_FOUND.value()))
								.error(HttpStatus.NOT_FOUND)
								.localDateTime(LocalDateTime.now())
								.path(wb.getDescription(false))
								.message(ex.getMessage())
								.exception(ex.getLocalizedMessage())
								.traceID(Instant.now().toEpochMilli())
								.errorDetails(stackTrace)
								.build();
				
		
		return ResponseEntity.ok(apiResponse);
		
	}

}
