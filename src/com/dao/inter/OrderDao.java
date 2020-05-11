package com.dao.inter;

import com.vo.Order;

public interface OrderDao {
	public int addOrder(Order order) throws Exception;
}
