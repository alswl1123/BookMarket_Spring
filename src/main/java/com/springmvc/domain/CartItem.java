package com.springmvc.domain;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable{
	
	private static final long serialVersionUID = 3636831123198280235L;
	
	private Book book;      //도서
	private int quantity;   //도서 개수
	private int totalPrice; //도서 가격
	
	//마우스 우클릭 > Source > Generate Constructors from Superclass > 'Object' Generate
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	//마우스 우클릭 > Source > Generate Constructors using Fields > book 필드 Generate 하고 일부 수정
	public CartItem(Book book) {
		super();
		this.book = book;
		this.quantity = 1; //추가됨
		this.totalPrice = book.getUnitPrice(); //추가됨
	}

	//마우스 우클릭 > Source > Generate Getters and Setters > 모든 필드 선택 후 Generate 하고 일부 수정
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
		this.updateTotalPrice(); //추가됨
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateTotalPrice(); //추가됨
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void updateTotalPrice() { //setTotalPrice() 메서드를 바꿈
		totalPrice = this.book.getUnitPrice() * this.quantity;
	}

	//마우스 우클릭 > Source > Generate hashCode() and equals() 선택 > book 필드만 Generate
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if(book==null) {
			if(other.book != null)
				return false;
		} else if(!book.equals(other.book))
			return false;
		return true;
	}
}
