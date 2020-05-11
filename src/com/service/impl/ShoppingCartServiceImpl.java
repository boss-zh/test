package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.service.inter.ShoppingCartService;
import com.vo.Product;

public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Override
	public void addToCart(HttpSession session,Product product) {
		
		List<Product> list = (List<Product>)session.getAttribute("shoppingCart");
		if(list==null){
			//第一次买东西
			System.out.println("第一次买东西");
			list = new ArrayList<Product>();
	
			product.setShoppingCarSum(1);
			list.add(product);//加入购物车
			
		}else{
			//第二次买东西
			System.out.println("第二次买东西");
			int pid = product.getPid();
			
			boolean ifBeforeBuy = false;
			
			//查看一下 以前 是否 买过
			for(Product oldProduct:list){
				
				if(oldProduct.getPid()==pid){//如果以前买过这个东西
					//数量+1
					System.out.println("数量+1");
					
					int shoppingCarSum = oldProduct.getShoppingCarSum();
					oldProduct.setShoppingCarSum(shoppingCarSum + 1);
					
					ifBeforeBuy = true;
					
					break;
					
					
				}
				
			}
			
			if(!ifBeforeBuy){//之前如果没买过
				System.out.println("之前没买过");
				product.setShoppingCarSum(1);
				list.add(product);//加入购物车
			}
			
		}
		
		
		session.setAttribute("shoppingCart", list);
		
		
	}

}
