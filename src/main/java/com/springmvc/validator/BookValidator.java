package com.springmvc.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springmvc.domain.Book;

public class BookValidator implements Validator {
	
	@Autowired
	private javax.validation.Validator beanValidator; //bean validation(JSR-380 validator)의 인스턴스 선언
	
	private Set<Validator> springValidators; //spring validation(Validator 인터페이스)의 인스턴스 선언
	
	public BookValidator() { //BookValidator 클래스의 생성자
		springValidators = new HashSet<Validator>();
	}
	
	public void setSpringValidators(Set<Validator> springValidators) { //springValidators 의 Setter() 메서드
		this.springValidators = springValidators;
	}
	
	public boolean supports(Class<?> clazz) { //Book 클래스의 유효성 검사 메서드
		return Book.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) { //Book 클래스의 유효성 검사 메서드
		//Bean Validation 설정
		Set<ConstraintViolation<Object>> violations = beanValidator.validate(target);
		for(ConstraintViolation<Object> violation : violations) {
			//오류 발생 필드 저장
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage(); //오류 발생 메시지 저장
			//오류가 발생된 필드와 메시지를 Errors 객체에 저장
			errors.rejectValue(propertyPath, "", message);
		} //bean validation 오류 저장
		
		for(Validator validator: springValidators) {
			validator.validate(target, errors); //발생된 오류 정보를 전달
		} //spring validation 오류 저장
	}
}
