package com.dao.inter;

import java.util.List;
import com.vo.Product;
//商品管理的DAO接口
public interface ProductDao {
	//一.增加
	public int addProduct(Product product) throws Exception;
	//二.删除
	public int deleteProduct(Product product) throws Exception;
	//三.修改
	public int updateProduct(Product product) throws Exception;
	//四.查1
	public Product getProductById(Integer pid) throws Exception;
	//五.按SQL语句查
	public List<Product> getPageByQuery(String sql) throws Exception;
	//六.查询总共有多少条记录
	public int getTotalRecordSum(String sql) throws Exception;
	
}
