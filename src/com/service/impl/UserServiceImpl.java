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
	 *公司: 蓝桥杯软件学院
	 *作者: 张兆亿
	 *时间: 2016年9月4日
	 *功能: 用户登录
	 *@return 如果根据用户名和密码找到该用户 返回该用户对象  没找到的话 返回null
	 */
	public User login(String username, String password) throws Exception{
		
		User user = null;
		
		String sql = "select * from user1 where username='" + username+ "' and password='" + password + "'";
		List<User> list = dao.getPageByQuery(sql);
		if(list.size()>0){
			//用户存在
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
			throw new Exception("激活失败");
		}
		
		return flag;
	}

}
