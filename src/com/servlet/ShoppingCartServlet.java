package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.impl.ProductServiceImpl;
import com.service.impl.ShoppingCartServiceImpl;
import com.vo.Product;

public class ShoppingCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getParameter("action");
		if ("addToCart".equals(action)) {
			this.addToCart(request, response);
		} else if ("queryShoppingCart".equals(action)) {
			this.queryShoppingCart(request, response);
		}

	}

	public void addToCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = "";
		// 一.填充数据
		String pid = request.getParameter("pid");
		System.out.println("pid=" + pid);

		try {
			ProductServiceImpl productService = new ProductServiceImpl();
			Product product = productService.getProductById(pid);
			
			ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl();

			// true 如果有和当前用户(浏览器窗口)相关联的session 返回这个 session
			// 如果没有 就为这个用户(浏览器窗口)创建一个对应的session

			// false 如果有和当前用户(浏览器窗口)相关联的session 返回这个 session
			// 没有相关联的session 返回null
			HttpSession session = request.getSession(true);// 必须要使用true
			shoppingCartService.addToCart(session, product);

			// 三.转发视图
			target = "/WEB-INF/jsp/user/addToShoppingCart.jsp";
			request.setAttribute("product", product);
		} catch (Exception e) {
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
		// 二.调用业务逻辑

		request.getRequestDispatcher(target).forward(request, response);
	}

	public void queryShoppingCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = "";
		// 一.填充数据
		// 二.调用业务逻辑
		HttpSession session = request.getSession(true);

		List<Product> list = (List<Product>) session
				.getAttribute("shoppingCart");
		
		if(list==null||list.size()==0){
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg", "购物车内暂时没有商品，登录后将显示您之前加入的商品 ");
		}else{
			request.setAttribute("list", list);
			target = "/WEB-INF/jsp/user/shoppingCart.jsp";
		}
		// 三.转发视图
		
		request.getRequestDispatcher(target).forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
