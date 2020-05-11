package com.service.inter;

import java.util.List;

import com.page.OrderCondition;
import com.page.PageInfo;
import com.vo.Product;

public interface UserProductService {
	// 指定种类下的所有商品的数量
	public int getTotalRecordSumByCategory(String cid) throws Exception;

	// 按查询条件查询 查询后总共有多少条记录
	public int getTotalRecordSumBySearchCondition(Product product) throws Exception;
	
	public List<Product> getPageByQuery(Product product, PageInfo pageInfo) throws Exception;

	//查询指定商品种类下的所有商品  + 排序 然后分页
	public List<Product> getAllByPageByCategory(PageInfo pageInfo,String cid,OrderCondition orderConditionObj) throws Exception;
	
	// 查1
	public Product getProductById(String pid) throws Exception;
}
