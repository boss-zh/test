package com.service.inter;

import java.util.List;

import com.vo.Order;
import com.vo.Product;

public interface OrderService {

	//���ض������orderNo,����������û�������� + �������ʾ
	public String addOrder(Order order,List<Product> shoppingCart) throws Exception;
}
