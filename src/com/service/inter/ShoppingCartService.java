package com.service.inter;

import javax.servlet.http.HttpSession;

import com.vo.Product;

public interface ShoppingCartService {

	//把商品加入购物车
	public void addToCart(HttpSession session,Product product);
}
