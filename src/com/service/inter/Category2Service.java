package com.service.inter;

import java.util.List;

import com.vo.Category2;

public interface Category2Service {

	//添加
	public int addCategory2(Category2 category) throws Exception;
	//查询一级商品种类下的二级商品种类数量
	public int getCategory2SumByCategory1(int cid) throws Exception;
	//查询一级商品种类下对应的二级商品种类
	public List<Category2> getAllCategory2ByCategory1(int cid) throws Exception;
	//查1
	public Category2 getCategoryById(String cid) throws Exception;

	//根据商品种类名字得到Category2
	public Category2 getCategoryByName(String cname) throws Exception;
	//查询指定二级种类的父亲一级种类的cid  
	//形参: cid 二级种类的cid
	//返回值: 所属一级种类的cid
	public int getParentCategoryById(int cid) throws Exception;
}
