package com.dao.inter;

import java.util.List;

import com.vo.Category;
import com.vo.Category2;

public interface Category2Dao {

	//1.添加
	public int addCategory2(Category2 category) throws Exception;
	
	//2.根据sql查询 总共有多少条记录
	public int getTotalRecordCount(String sql) throws Exception;
	
	//3.按SQL语句查
	public List<Category2> getPageByQuery(String sql) throws Exception;
	
	//4.查1
	public Category2 getCategoryById(Integer cid) throws Exception;
}
