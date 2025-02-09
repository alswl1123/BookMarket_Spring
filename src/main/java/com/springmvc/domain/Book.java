package com.springmvc.domain;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.springmvc.validator.BookId;

public class Book implements Serializable {
	
	//serialVersionUID 는 직렬화를 할 때 메타 정보로 저장되는 id 값. 서로 다른 자바 컴파일러 구현체 사이에서도 동일한 serialVersionUID 값을 얻으려면
	//명시적으로 serialVersionUID 값을 선언해야 함. 가능한 private 으로 선언.
	//serialVersionUID 대신 @SuppressWarnings 로 변경해도 됨. @SuppresWarnings("serial") 을 클래스 위에 선언.
	private static final long serialVersionUID = -7715651009026349175L;
	
	@BookId //사용자 정의 애너테이션
	@Pattern(regexp = "ISBN[1-9]+", message = "{Pattern.NewBook.bookId}")
	private String bookId; // 도서 ID

	@Size(min = 4, max = 50, message = "{Size.NewBook.name}")
	private String name; // 도서명

	@Min(value = 0, message = "{Min.NewBook.unitPrice}")
	@Digits(integer = 8, fraction = 2, message = "{Digits.NewBook.unitPrice}")
	@NotNull(message = "{NotNull.NewBook.unitPrice}")
	private int unitPrice;		     // 가격
	private String author;		     // 저자
	private String description;		 // 설명
	private String publisher;   	 // 출판사
	private String category;	     // 분류
	private long unitsInStock;		 // 재고 수
	private String releaseDate;      // 출판일(월/년)
	private String condition;		 // 신규 도서 또는 중고 도서 또는 전자책
	private MultipartFile bookImage; // 도서 이미지
	private String fileName;         // 문자열 타입의 fileName 필드 추가
	
	// 기본 생성자 : 마우스 우클릭 > Source > Generate Constructors from Superclass > Object() 체크 > Generate 클릭
	public Book() { 
		super();
		// TODO Auto-generated constructor stub
	}

	// 일반 생성자 : 마우스 우클릭 > Source > Generate Constructor using Fields > bookId, naem, unitPrice 체크 > Generate 클릭
	public Book(String bookId, String name, int unitPrice) { 
		super();
		this.bookId = bookId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	// 모든 필드의 getter/setter
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public MultipartFile getBookImage() {
		return bookImage;
	}

	public void setBookImage(MultipartFile bookImage) {
		this.bookImage = bookImage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
