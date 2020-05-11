package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.page.OrderCondition;
import com.page.PageInfo;
import com.service.impl.Category2ServiceImpl;
import com.service.impl.UserProductServiceImpl;
import com.service.inter.Category2Service;
import com.service.inter.UserProductService;
import com.vo.Category2;
import com.vo.Product;

public class UserProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("getProductListByCategory".equals(action)) {
			this.getProductListByCategory(request, response);
		}else if ("getPageByQuery".equals(action)) {
			this.getPageByQuery(request, response);
		}else if ("getOneProduct".equals(action)) {
			this.getOneProduct(request, response);
		}else if ("getOneProductUrl".equals(action)) {
			this.getOneProductUrl(request, response);
		}
		
	}

	public void getProductListByCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in UsreProductServlet getProductListByCategory!!!");
		String target = "";
		// 一.填充数据
		String cid = request.getParameter("cid");// 62 查手机
		System.out.println(cid);

		String requestPage = request.getParameter("requestPage");
		System.out.println(requestPage);
		
		
		String orderCondition = request.getParameter("orderCondition");
		String ascOrDesc = request.getParameter("ascOrDesc");
		
		OrderCondition orderConditionObj = new OrderCondition();
		
		if(orderCondition!=null&&!orderCondition.trim().equals("")){
			orderConditionObj.setOrderCondition(orderCondition);
			orderConditionObj.setAscOrDesc(ascOrDesc);
		}
		
		
		
		// 二.调用业务逻辑
		PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage), 8);// 每页显示8条记录

		List<Product> list;
		try {
			// 查询指定商品种类下 总共有多少条商品记录
			UserProductServiceImpl service1 = new UserProductServiceImpl();

			int totalRecordSum = service1.getTotalRecordSumByCategory(cid);
			pageInfo.setTotalRecordCount(totalRecordSum);
			// 根据商品种类 查询 商品
			UserProductServiceImpl service = new UserProductServiceImpl();
			list = service.getAllByPageByCategory(pageInfo, cid,orderConditionObj);
			// 三.转发视图
			request.setAttribute("list", list);
			request.setAttribute("cid", cid);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("orderConditionObj", orderConditionObj);
			target = "/WEB-INF/jsp/user/list.jsp";
		} catch (Exception e) {
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
		}

		request.getRequestDispatcher(target).forward(request, response);
	}

	public void getPageByQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in getPageByQuery");
		String cid = request.getParameter("cid");
		
		
		System.out.println("cid=" + cid);
		if (cid != null && !cid.trim().equals("")) {
			// 点菜单的商品种类的时候 使用这个
			System.out.println("点菜单过来的");
			this.getProductListByCategory(request, response);
		} else {
			// 搜索框使用
			System.out.println("搜索框使用");
			this.getPageByQuery2(request, response);
		}
	}

	// 搜索框使用这个
	public void getPageByQuery2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in UserProductServlet--->getPageByQuery1234");
		String target = "";

		// 一.填充数据

		String requestPage = request.getParameter("requestPage");

		System.out.println("requestPage=" + requestPage);
		if (requestPage == null) {
			requestPage = "1";
		}

		String searchCondition = request.getParameter("searchCondition");
		System.out.println("searchCondition=" + searchCondition);
		
		String orderCondition = request.getParameter("orderCondition");
		System.out.println("orderCondition=" + orderCondition);
		
		String ascOrDesc = request.getParameter("ascOrDesc");
		System.out.println("ascOrDesc=" + ascOrDesc);
		// 二.调用业务逻辑
		PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage), 8);

		// 按查询条件查询 计算查询结果总共有多少条记录
		UserProductServiceImpl service1 = new UserProductServiceImpl();

		Product product = new Product();

		// 如果是按商品种类名称查询 需要把传过来的商品种类名称转换成cid
		Category2Service service = new Category2ServiceImpl();

		
		try {
			Category2 category  = service.getCategoryByName(searchCondition);
			if (searchCondition != null && !searchCondition.trim().equals("")) {
				product.setPname(searchCondition);

				product.setDianpuName(searchCondition);

				if (category != null) {
					product.setCid(category.getCid());
				}

			}

			OrderCondition orderConditionObj = new OrderCondition();
			//设置排序条件
			if(orderCondition!=null&&!orderCondition.trim().equals("")){
				orderConditionObj.setOrderCondition(orderCondition);
				orderConditionObj.setAscOrDesc(ascOrDesc);
				product.setOrderConditionObj(orderConditionObj);
			}
			
			
			
			int totalRecordSum = service1
					.getTotalRecordSumBySearchCondition(product);
			pageInfo.setTotalRecordCount(totalRecordSum);

			UserProductServiceImpl service2 = new UserProductServiceImpl();
			List<Product> list = service2.getPageByQuery(product, pageInfo);

			// 三.转发视图
			target = "/WEB-INF/jsp/user/list.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("searchCondition", searchCondition);
			request.setAttribute("orderConditionObj", orderConditionObj);
		
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}

	
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	//查询单一商品
	public void getOneProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		//1.填充数据
		String pid = request.getParameter("pid");
		
		//2.调用业务逻辑
		UserProductService service = new UserProductServiceImpl();
		try {
			Product product = service.getProductById(pid);
			request.setAttribute("product", product);
			target = "/WEB-INF/jsp/user/product.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
		}
		//3.转发视图
		request.getRequestDispatcher(target).forward(request, response);;
	}
	
	public void getOneProductUrl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("使用URL重写方式实现会话跟踪");
		String target = "";
		//一.填充数据
		String pid = request.getParameter("pid");
		
		//二.调用业务逻辑
		
		try {
			UserProductServiceImpl service = new UserProductServiceImpl();
			Product product = service.getProductById(pid);
			
			
			String url = "ShoppingCartServlet?action=addToCart&pid=" + pid;
			
			// 使用URL重写技术 实现 session  适用于 cookie 被禁用的情况
			
			HttpSession session = request.getSession(true);
			
			//如果cookie 被禁用了  帮你重写url
			//如果没有禁用cookie 原样返回这个url
			String afterURL = response.encodeURL(url);
			System.out.println(afterURL);
			
			//三.转发视图
			request.setAttribute("product", product);
			request.setAttribute("afterURL", afterURL);
			target = "/WEB-INF/jsp/user/productURL.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
