package com.springmvc.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy=BookIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BookId { //생김새 주의. 만들 때는 클래스로 생성
	String message() default "{BookId.NewBook.bookId}";
	Class<?>[] groups() default{};
	public abstract Class<? extends Payload>[] payload() default{};
}
