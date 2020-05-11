package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.page.PageInfo;
import com.service.impl.Category2ServiceImpl;
import com.service.impl.CategoryServiceImpl;
import com.service.inter.Category2Service;
import com.service.inter.CategoryService;
import com.vo.Category;

public class CategoryServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//分发请求
		String action = request.getParameter("action");
		
		if("add".equals(action)){
			this.add(request, response);
		}else if("getAll".equals(action)){	
			this.getAll(request,response);
		}else if("delete".equals(action)){	
			this.delete(request,response);
		}else if("getOneForUpdate".equals(action)){	
			this.getOneForUpdate(request,response);
		}else if("update".equals(action)){	
			this.update(request,response);
		}else if("getAllByPage".equals(action)){	
			this.getAllByPage(request,response);
		}else if("getPageByQuery".equals(action)){	
			this.getPageByQuery(request,response);
		}
		
		
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		//一.填充数据
		String cname = request.getParameter("cname");
		String cdesc = request.getParameter("cdesc");
		
		//二.调用业务逻辑
		Category category = new Category();
		category.setCname(cname);
		category.setCdesc(cdesc);
		
		CategoryService service = new CategoryServiceImpl();
		try {
			service.addCategory(category);
			request.setAttribute("msg", "添加一级商品种类成功");
		} catch (Exception e) {
			request.setAttribute("msg", "添加一级商品种类失败");
			e.printStackTrace();
		}
		//三.转发视图
		target = "/WEB-INF/msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(target);
		
		rd.forward(request, response);
	}
	
	public void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
System.out.println("test!!!");
		
		String target = "";
		//1.填充数据
		//2.调用业务逻辑
		CategoryService service = new CategoryServiceImpl();
		
		try {
			List<Category> list = service.getAllCategorys();
			
			request.setAttribute("list", list);
			
			target = "/WEB-INF/jsp/admin/category/categoryMain.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "查询一级商品种类失败!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.转发视图
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		//1.填充数据
		String cid = request.getParameter("cid");
		
		
		try {
			
			//先查询有没有对应的二级商品种类 如果有 不能删除
			Category2Service service = new Category2ServiceImpl();
			
			int count = service.getCategory2SumByCategory1(Integer.parseInt(cid));
			
			if(count>0){
				request.setAttribute("msg", "删除失败");
				request.setAttribute("msgDetail", "已找到子记录 请先删除子种类 再继续删除");
			}else{
				//2.调用业务逻辑
				CategoryService service2 = new CategoryServiceImpl();
				
				service2.deleteCategoryById(cid);
				request.setAttribute("msg", "删除成功!");
			}
		
			this.getPageByQuery(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
			//3.转发视图
			request.getRequestDispatcher(target).forward(request, response);
		}
		
	}
	
	public void getOneForUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		//1.填充数据
		String cid = request.getParameter("cid");
		//2.调用业务逻辑
		CategoryService service = new CategoryServiceImpl();
		
		try {
			Category category = service.getCategoryById(cid);
			request.setAttribute("category", category);
			target = "/WEB-INF/jsp/admin/category/updateCategory.jsp";
		} catch (Exception e) {
			
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target= "/WEB-INF/msg.jsp";
		}
		//3.转发视图
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String target = "";
		//1.填充数据
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		String cdesc = request.getParameter("cdesc");
		Category category = new Category();
		category.setCid(Integer.parseInt(cid));
		category.setCname(cname);
		category.setCdesc(cdesc);
		//2.调用业务逻辑
		CategoryService service = new CategoryServiceImpl();
		try {
			service.updateCategory(category);
			request.setAttribute("msg", "修改一级商品种类成功!");
			this.getPageByQuery(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
			//3.转发视图
			request.getRequestDispatcher(target).forward(request,response);
		}
		
	}
	
	public void getAllByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		//1.填充数据
		String requestPage = request.getParameter("requestPage");
		
		try {
			
			//查询一共多少条记录
			CategoryService service = new CategoryServiceImpl();
			
			int totalRecordCount = service.getTotalRecordCount();
			
			PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage));
			pageInfo.setTotalRecordCount(totalRecordCount);
			
			//2.调用业务逻辑
			CategoryService service2 = new CategoryServiceImpl();
			
			List<Category> list = service2.getAllByPage(pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			target = "/WEB-INF/jsp/admin/category/categoryMain.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.转发视图
		request.getRequestDispatcher(target).forward(request, response);
		
	}
	
	public void getPageByQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String target = "";
		//1.填充数据
		String searchCondition = request.getParameter("searchCondition");
		
		String requestPage = request.getParameter("requestPage");
		if(requestPage==null){
			
			requestPage = "1";
		}
		
		Category category = new Category();
		
		if(searchCondition!=null&&!searchCondition.equals("")){
			category.setCname(searchCondition);
			category.setCdesc(searchCondition);
		}
		
		try {
			
			
			//按照条件查询一共多少条记录
			CategoryService service = new CategoryServiceImpl();
			
			int totalRecordCount = service.getTotalRecordCount(category);
			PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage));
			pageInfo.setTotalRecordCount(totalRecordCount);
			//2.调用业务逻辑
			CategoryService service2 = new CategoryServiceImpl();

			List<Category> list = service2.getPageByQuery(category, pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("searchCondition", searchCondition);
			request.setAttribute("pageInfo", pageInfo);
			target = "/WEB-INF/jsp/admin/category/categoryMain.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.转发视图
		request.getRequestDispatcher(target).forward(request, response);
		
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
