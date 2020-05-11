package com.dao.inter;

import java.util.List;

import com.vo.Category;

public interface CategoryDao {

	//1.添加
	public int addCategory(Category category) throws Exception;
	//2.删除
	public int deleteCategory(Category category) throws Exception;
	//3.修改
	public int updateCategory(Category category) throws Exception;
	//4.查1
	public Category getCategoryById(int id) throws Exception;
	//5.按SQL语句查
	public List<Category> getPageByQuery(String sql) throws Exception;
	//6.根据sql查询 总共有多少条记录
	public int getTotalRecordCount(String sql) throws Exception;
}
