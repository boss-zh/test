package com.dao.inter;

import java.util.List;
import com.vo.User;

public interface UserDao {
	//一.增加
	public int addUser(User user) throws Exception;
	//二.删除
	public int deleteUser(User user) throws Exception;
	//三.修改
	public int updateUser(User user) throws Exception;
	
	//四.查1
	public User getUserById(Integer userid) throws Exception;
	
	//五.按SQL语句查
	public List<User> getPageByQuery(String sql) throws Exception;
	
	//六.查询总共有多少条记录
	public int getTotalRecordSum(String sql) throws Exception;
	
}
