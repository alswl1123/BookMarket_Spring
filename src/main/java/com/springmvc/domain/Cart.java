package com.springmvc.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private String cartId;   				 // 장바구니 ID
	private Map<String, CartItem> cartItems; //장바구니 항목
	private int grandTotal;                  //총액
	
	//마우스 우클릭 > Source > Generate Constructors from Superclass > 'Object' Generate 하고 일부 수정
	public Cart() {
		cartItems = new HashMap<String, CartItem>();
		grandTotal = 0;
	}

	//마우스 우클릭 > Source > Generate Constructors using Fields > cartId 필드만 Generate 하고 일부 수정
	public Cart(String cartId) {
		this(); //수정됨
		this.cartId = cartId;
	}

	//마우스 우클릭 > Source > Generate Getters and Setters > 모든 필드 선택 후 Generate 하고 일부 수정
	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public int getGrandTotal() {
		return grandTotal;
	}

	public void updateGrandTotal() {
		grandTotal = 0;
		for(CartItem item : cartItems.values()) {
			grandTotal = grandTotal + item.getTotalPrice();
		}
	}
	
	//마우스 우클릭 > Source > Generate hashCode() and equals() 선택 > cartId 필드 Generate
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
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
			Cart other = (Cart) obj;
			if(cartId==null) {
				if(other.cartId != null)
					return false;
			} else if(!cartId.equals(other.cartId))
				return false;
			return true;
		}
	
	
	
	
	
}
