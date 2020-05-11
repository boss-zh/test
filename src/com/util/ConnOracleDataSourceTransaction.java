package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//Tomcat整合数据库连接池
public class ConnOracleDataSourceTransaction {

	// 定义ThreadLocal静态变量，确定存取类型为Connection
	private static ThreadLocal<Connection> connHolder = new ThreadLocal<Connection>();

	private static Context context;
	private static DataSource ds;

	static {

		// JNDI的资源池只需要创建一次就可以了 所以把这段代码放到静态初始化块中执行
		try {
			context = new InitialContext();// 创建JNDI资源池
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/jd");// 根据资源名
																		// 找到对应的资源
																		// 该资源名为server.xml配置的资源名name
			System.out.println(ds);
		} catch (NamingException e) {

			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = connHolder.get();
		System.out.println("**********look here***************");
		System.out.println("conn=" + conn);
		if (conn == null) {
			try {
				conn = ds.getConnection();
				System.out.println("conn=" + conn);
				// 将Connection设置到ThreadLocal
				connHolder.set(conn);
			} catch (SQLException e) {
				System.out.println("创建数据库连接失败");
				e.printStackTrace();
			}
		}

		return conn;
	}
	
	public static void closeConnection() {

		Connection conn = connHolder.get();
		System.out.println("**********关闭数据库***************");
		System.out.println("conn=" + conn);
		if (conn != null) {
			
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("关闭数据库失败");
				e.printStackTrace();
			}
		}

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
		System.out.println("开启事务");
		Connection conn = connHolder.get();
		System.out.println("conn=" + conn);
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
		System.out.println("提交事务");
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
		System.out.println("回滚事务");
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
		System.out.println(ConnOracleDataSourceTransaction.getConnection());
	}
}
