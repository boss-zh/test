package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dao.inter.OrderDao;
import com.util.ConnOracleDataSourceTransaction;
import com.vo.Order;

public class OrderDaoImpl implements OrderDao {


	private Connection conn;

	public OrderDaoImpl() {

		conn = ConnOracleDataSourceTransaction.getConnection();
	}

	@Override
	public int addOrder(Order order) throws Exception {
		int orderId = 0;
		// 3.建立通道
		String sql = "insert into order1 values(seq_order.nextval,?,sysdate,?,?,?,?,0)";
		PreparedStatement pstmt = null;

		try {

		
			pstmt = conn.prepareStatement(sql,new String[]{"orderId"});

			pstmt.setString(1, order.getOrderNo());
			pstmt.setDouble(2, order.getOrderTotalPrice());
			pstmt.setString(3, order.getOrderDesc());
			pstmt.setString(4, order.getReceivedPersonInfo());
			pstmt.setInt(5, order.getUserId());
			// 4.执行并返回结果集
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			orderId = rs.getInt(1);
			System.out.println("#################################################");
			System.out.println("orderId=" + orderId);
		} catch (SQLException e) {
			
			System.out.println("建立通道或添加商品种类失败");
			e.printStackTrace();
			
			throw new Exception("商品种类添加失败");//异常转译
		}/* finally {
			// 5.关闭
			ConnOracle.closeConnection(null, pstmt, conn);
		}*/

		return orderId;
	}

	public static void main(String[] args) throws Exception{
		OrderDaoImpl dao = new OrderDaoImpl();
		
		Order order = new Order();
		order.setOrderNo("aaa");
		order.setOrderTotalPrice(8000);
		order.setOrderDesc("赠品要苹果手机壳");
		order.setReceivedPersonInfo("张老师,山东省济南市,132****1230");
		order.setUserId(1);
		
		int count = dao.addOrder(order);
		System.out.println(count);
	}
}
