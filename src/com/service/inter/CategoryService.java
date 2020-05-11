package com.service.inter;

import java.util.List;

import com.page.PageInfo;
import com.vo.Category;

public interface CategoryService {

	//添加
	public int addCategory(Category category) throws Exception;
	//查所有
	public List<Category> getAllCategorys() throws Exception;
	//删除单个一级商品种类
	public int deleteCategoryById(String cid) throws Exception;
	//查1
	public Category getCategoryById(String cid) throws Exception;
	//修改
	public int updateCategory(Category category) throws Exception;
	//查询所有后分页
	public List<Category> getAllByPage(PageInfo pageInfo) throws Exception;
	//查询总共有多少条记录
	public int getTotalRecordCount() throws Exception;
	//根据条件查询 然后分页
	public List<Category> getPageByQuery(Category category,PageInfo pageInfo) throws Exception;
	//根据条件查询 总共有多少条记录
	public int getTotalRecordCount(Category category) throws Exception;
}
