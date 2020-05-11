package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.impl.JavaMailServiceImpl;

public class MailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String target = "";
		
		//一.填充数据
		String from = request.getParameter("from");
		System.out.println(from);
		String to = request.getParameter("to");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		//二.调用业务逻辑
		JavaMailServiceImpl service = new JavaMailServiceImpl();
		
		boolean isSuccessFlag = service.sendTextMail(from, to, subject, content);
		
		if(isSuccessFlag){
			request.setAttribute("msg", "发送成功");
		}else{
			request.setAttribute("msg", "发送失败");
		}
		
		target = "/WEB-INF/msg.jsp";
		//三.转发视图
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
