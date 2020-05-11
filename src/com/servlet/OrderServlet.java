package com.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.impl.OrderServiceImpl;
import com.service.inter.OrderService;
import com.vo.Order;
import com.vo.Product;
import com.vo.User;

public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String action = request.getParameter("action");

		if ("checkOrder".equals(action)) {
			this.checkOrder(request, response);
		} else if ("placeOrder".equals(action)) {
			this.placeOrder(request, response);
		}

	}

	public void checkOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in checkOrder");
		String target = "";
		// һ.�������
		// ��.����ҵ���߼�
		// �ж��Ƿ��¼
		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");
		if (user != null) {
			// ���� ����ҳ

			// ��ѯ���ﳵ��Ķ��� �ڽ���ҳ ����ʾһ�� Ϊ��ȷ��
			List<Product> list = (List<Product>) session
					.getAttribute("shoppingCart");
			// ��.ת����ͼ
			request.setAttribute("list", list);

			target = "/WEB-INF/jsp/user/jiesuan.jsp";
		} else {
			// ������½ҳ��
			target = "/jsp/user/login.jsp";
			request.setAttribute("toWhere", "jiesuan");
		}
		// ��.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void placeOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String target = "";
		// 1.�������
		String nickName = request.getParameter("nickName");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");

		String receivedPersonInfo = nickName + "  " + address + "  "
				+ phoneNumber;

		HttpSession session = request.getSession(true);

		List<Product> shoppingCart = (List<Product>) session
				.getAttribute("shoppingCart");
		User user = (User) session.getAttribute("user");
		int userId = user.getUserid();

		double orderTotalPrice = computeOrderTotalPrice(shoppingCart);
		Order order = new Order();
		
		long currentTime = System.currentTimeMillis();
		
		order.setOrderNo(userId + "" + currentTime);
		order.setOrderTotalPrice(orderTotalPrice);
		order.setReceivedPersonInfo(receivedPersonInfo);
		order.setUserId(userId);
		// 2.����ҵ���߼�
		OrderService service = new OrderServiceImpl();

		try {
			String orderNo = service.addOrder(order, shoppingCart);
			//��չ��ﳵ
			session.removeAttribute("shoppingCart");
			target = "/WEB-INF/jsp/user/placeOrderSuccess.jsp";
			request.setAttribute("orderNo", orderNo);
			request.setAttribute("orderTotalPrice", orderTotalPrice);
		} catch (Exception e) {
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
		// 3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}

	public double computeOrderTotalPrice(List<Product> shoppingCart) {
		double productTotalPrice = 0;

		for (Product product : shoppingCart) {

			int shoppingCarSum = product.getShoppingCarSum();
			double buyPrice = product.getPrice();
			double singleProductTotalPrice = buyPrice * shoppingCarSum;
			productTotalPrice = productTotalPrice + singleProductTotalPrice;

		}

		return productTotalPrice;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
