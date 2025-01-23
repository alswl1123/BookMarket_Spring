package com.springmvc.repository;

import com.springmvc.domain.Cart;

public interface CartRepository {

	Cart create(Cart cart);
	Cart read(String cartId);

}
