package br.com.product.catalog.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestControllerAdvice
public class ValidationErrorHandler{	
	private int status_code;
	private String message;
	
	@Autowired @JsonInclude(Include.NON_DEFAULT)
	private MessageSource messageSource;	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ValidationErrorHandler handle(MethodArgumentNotValidException exception) {
		
		FieldError fieldErrors = exception.getBindingResult().getFieldError();
		String mensagem = messageSource.getMessage(fieldErrors, LocaleContextHolder.getLocale());
		int statusCode = HttpStatus.BAD_REQUEST.value();
		
		ValidationErrorHandler erroHandler = new ValidationErrorHandler();
		
		erroHandler.setMessage(mensagem);
		erroHandler.setStatus_code(statusCode);
				
		return erroHandler;
	}
}