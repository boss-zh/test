package com.service.impl;

import java.util.List;

import com.dao.impl.OrderDaoImpl;
import com.dao.impl.OrderDetailDaoImpl;
import com.dao.impl.ProductDaoImpl;
import com.dao.inter.OrderDao;
import com.dao.inter.OrderDetailDao;
import com.dao.inter.ProductDao;
import com.service.inter.OrderService;
import com.util.ConnOracleTransaction;
import com.util.ConnOracleDataSourceTransaction;
import com.vo.Order;
import com.vo.OrderDetail;
import com.vo.Product;

public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = new OrderDaoImpl();

	public String addOrder(Order order, List<Product> shoppingCart)
			throws Exception {
		String orderNo = order.getOrderNo();
		//��������
		ConnOracleDataSourceTransaction.beginTransaction();
		int orderId;
		try {
			 orderId = orderDao.addOrder(order);
		} catch (Exception e) {
			ConnOracleDataSourceTransaction.rollbackTransaction();
			e.printStackTrace();
			throw new Exception("�µ�ʧ��");
		}

		for (Product product : shoppingCart) {

			int pid = product.getPid();

			int shoppingCarSum = product.getShoppingCarSum();
			double buyPrice = product.getPrice();

			// ��Ӷ�����ϸ
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(orderId);
			orderDetail.setPid(pid);
			orderDetail.setBuyPrice(buyPrice);
			orderDetail.setBuySum(shoppingCarSum);

			OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
			try {
				orderDetailDao.addOrderDetail(orderDetail);
			} catch (Exception e) {
				ConnOracleDataSourceTransaction.rollbackTransaction();
				e.printStackTrace();
				break;
			}

			// �޸���Ʒ�������
			ProductDao productDao = new ProductDaoImpl();

			int productSum = product.getProductSum();

			if (shoppingCarSum <= productSum) {
				productSum = productSum - shoppingCarSum;
				product.setProductSum(productSum);
				try {
					productDao.updateProduct(product);
				} catch (Exception e) {
					ConnOracleDataSourceTransaction.rollbackTransaction();
					e.printStackTrace();
				}
			} else {
				ConnOracleDataSourceTransaction.rollbackTransaction();
				throw new Exception(product.getPname() + "��治��,����ʱѡ��������Ʒ,лл!");

			}

			ConnOracleDataSourceTransaction.commitTransaction();
		}

		return orderNo;
	}

}
