package com.springmvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //전역 예외 처리
public class CommonException { //모든 컨트롤러의 예외 처리 클래스 

	@ExceptionHandler(RuntimeException.class) //예외 클래스 설정(RuntimeException)
	private ModelAndView handleErrorCommon(Exception e) { //컨트롤러에서 발생되는 예외 처리 메서드 handleErrorCommon()
		ModelAndView modelAndView = new ModelAndView(); //ModelAndView 클래스의 modelAndView 인스턴스를 생성함
		modelAndView.addObject("exception", e); //모델 속성 exception 에서 예외 처리 클래스 RuntimeException을 저장
		modelAndView.setViewName("errorCommon"); //뷰 이름으로 errorCommon 을 설정해서 errorCommon.jsp 파일 출력
		return modelAndView; //modelAndView 인스턴스 반환
	}
}
