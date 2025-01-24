package com.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.springmvc.domain.Cart;
import com.springmvc.repository.CartRepository;

public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	public Cart create(Cart cart) {
		return cartRepository.create(cart);
	}
	
	public Cart read(String cartId) {
		return cartRepository.read(cartId);
	}

	public CartRepository getCartRepository() {
		return cartRepository;
	}

	@Override //마우스 우클릭 > Source > override 로도 작성 가능하다
	public void update(String cartId, Cart cart) {
		cartRepository.update(cartId, cart);
	}


	
	

}
