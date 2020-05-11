package com.util;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

//ר���������ô洢���̵Ĺ�����
public class SQLUtil {

	private Connection conn;
	
	public SQLUtil(){
		conn = ConnOracle.getConnection();
	}
	
	//���ô洢����
	public  void callProcedure(){
		//��.����ͨ��
		String sql = "{call proc_increase_salary(?,?)}";
		CallableStatement cstmt = null;
		
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, 10);
			
			//�����������
			cstmt.registerOutParameter(2,Types.VARCHAR);
			
			//��.ִ��
			cstmt.executeUpdate();
			
			
			String msg = cstmt.getString(2);
			System.out.println("�洢���̳ɹ�");
			System.out.println(msg);
		} catch (SQLException e) {
			System.out.println("���ô洢����ʧ��");
			e.printStackTrace();
		}finally{
			//��.�ر�
			ConnOracle.closeConnection(null, cstmt, conn);
			
			
		}
		
		
	}
	
	//һ�����߷���: ����ִ�г�DQL������κ�SQL���
	public int executeExceptDQL(String sql) throws Exception{
		int count;
		//��.����ͨ��
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			
			//��.ִ��
			count = stmt.executeUpdate(sql);//ִ�� ����dql�������е����,  dml  ��ddl
			//���ִ�е���dml ����ֵ�� ��Ӱ�������   ִ��ddl ���ص��� 0
			System.out.println("count=" + count);
			System.out.println("ִ�����ɹ�");
			
		} catch (SQLException e) {
			System.out.println("ִ�����ʧ��");
			e.printStackTrace();
			throw new Exception("ִ��ʧ��");
		}finally{
			//��.�ر�
			ConnOracle.closeConnection(null, stmt, conn);
		}
		
		return count;
	}
	
	
}