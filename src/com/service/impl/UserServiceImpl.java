package com.service.impl;

import java.util.List;

import com.dao.impl.UserDaoImpl;
import com.service.inter.UserService;
import com.util.SQLUtil;
import com.vo.User;

public class UserServiceImpl implements UserService{
	private UserDaoImpl dao = new UserDaoImpl();
	
	
	/**
	 * 
	 *��˾: ���ű����ѧԺ
	 *����: ������
	 *ʱ��: 2016��9��4��
	 *����: �û���¼
	 *@return ��������û����������ҵ����û� ���ظ��û�����  û�ҵ��Ļ� ����null
	 */
	public User login(String username, String password) throws Exception{
		
		User user = null;
		
		String sql = "select * from user1 where username='" + username+ "' and password='" + password + "'";
		List<User> list = dao.getPageByQuery(sql);
		if(list.size()>0){
			//�û�����
			user = list.get(0);
			
		}
		
		return user;
	}
	
	public boolean activeUser(String username) throws Exception{
		
		boolean flag = false;
		String sql = "update user1 set isactive=1 where username='" + username + "'";
		
		try {
			SQLUtil sqlUtil = new SQLUtil();
			int count = sqlUtil.executeExceptDQL(sql);
			if(count>0){
				flag = true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new Exception("����ʧ��");
		}
		
		return flag;
	}

}
