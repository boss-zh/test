package com.service.inter;

import java.util.List;

import com.vo.Order;
import com.vo.Product;

public interface OrderService {

	//返回订单编号orderNo,订单编号有用户表的主键 + 随机数表示
	public String addOrder(Order order,List<Product> shoppingCart) throws Exception;
}
