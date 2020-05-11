package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 公司:蓝桥软件学院 作者:zhangzy 时间:2017年8月30日 上午9:26:05 功能:连接数据库的工具类
 */
public class ConnOracleTransaction {

	// 定义ThreadLocal静态变量，确定存取类型为Connection
	private static ThreadLocal<Connection> connHolder = new ThreadLocal<Connection>();

	public static Connection getConnection() {
		Connection conn = connHolder.get();
		System.out.println("**********look here***************");
		System.out.println("conn=" + conn);
		if (conn == null) {
			String className = "oracle.jdbc.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String password = "tiger";
			// 1.加载驱动
			try {
				Class.forName(className);

			} catch (ClassNotFoundException e) {
				System.out.println("驱动类没有找到");
				e.printStackTrace();
			}

			// 2.创建连接
			try {
				conn = DriverManager.getConnection(url, user, password);
				System.out.println("conn=" + conn);
				// 将Connection设置到ThreadLocal
				connHolder.set(conn);
			} catch (SQLException e) {
				System.out.println("连接数据库失败");
				e.printStackTrace();
			}

		}

		return conn;
	}

	public static void closeConnection(ResultSet rs, Statement stmt,
			Connection conn) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("关闭通道失败");
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("关闭数据库连接失败");
				e.printStackTrace();
			}
		}

	}

	// 事务开启
	public static void beginTransaction() {
		
		Connection conn = connHolder.get();
		
		try {
			if (conn != null) {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false); // 手动提交
				}
			}
		} catch (SQLException e) {
		}
	}

	// 事务开启
	public static void closeTransaction() {
		
		Connection conn = connHolder.get();
		
		try {
			if (conn != null) {
				if (!conn.getAutoCommit()) {
					conn.setAutoCommit(true); // 自动提交
				}
			}
		} catch (SQLException e) {
		}
	}

	// 事务提交
	public static void commitTransaction() {
		
		Connection conn = connHolder.get();
		try {
			if (conn != null) {
				if (!conn.getAutoCommit()) {
					conn.commit();
				}
			}
		} catch (SQLException e) {
		}
	}

	// 事务回滚
	public static void rollbackTransaction() {
		System.out.println("rollbackTransaction");
		Connection conn = connHolder.get();
		System.out.println("conn=" + conn);
		try {
			if (conn != null) {
				if (!conn.getAutoCommit()) {
					conn.rollback();
				}
			}
		} catch (SQLException e) {
		}
	}

	public static void main(String[] args) {
		System.out.println(ConnOracleTransaction.getConnection());
	}
}
