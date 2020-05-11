package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dao.inter.OrderDetailDao;
import com.util.ConnOracle;
import com.util.ConnOracleDataSourceTransaction;
import com.vo.OrderDetail;

public class OrderDetailDaoImpl implements OrderDetailDao {
	
	private Connection conn;

	public OrderDetailDaoImpl() {
		
		conn = ConnOracleDataSourceTransaction.getConnection();
	}

	@Override
	public int addOrderDetail(OrderDetail orderDetail) throws Exception {
		int count = 0;
		// 3.建立通道
		String sql = "insert into orderDetail values(seq_orderDetail.nextval,?,?,?,?)";
		
		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, orderDetail.getOrderId());
			pstmt.setInt(2, orderDetail.getPid());
			pstmt.setDouble(3, orderDetail.getBuyPrice());
			pstmt.setInt(4, orderDetail.getBuySum());
			// 4.执行并返回结果集
			count = pstmt.executeUpdate();
											
		} catch (SQLException e) {
			System.out.println("建立通道失败");
			e.printStackTrace();
			throw new Exception("添加订单明细失败");
		} /*finally {
			// 5.关闭
			ConnOracle.closeConnection(null, pstmt, conn);
		}*/

		return count;
	}

	public static void main(String[] args) throws Exception{
		OrderDetailDaoImpl dao = new OrderDetailDaoImpl();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId(3);
		orderDetail.setPid(1);
		orderDetail.setBuyPrice(1);
		orderDetail.setBuySum(1);
		
		dao.addOrderDetail(orderDetail);
	}
}
