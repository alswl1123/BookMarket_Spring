package com.springmvc.exception;

@SuppressWarnings("serial")
public class BookIdException extends RuntimeException { //RuntimeException 을 보고 BookIdException 가 사용자 정의 Exception 인 것을 알 수 있다.

	private String bookId;
	
	public BookIdException(String bookId) { //생성자
		this.bookId = bookId;
	}
	
	public String getBookId() { //Getter() 메서드
		return bookId;
	}
}
