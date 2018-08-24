package com.atguigu.bookstore.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;
import com.atguigu.bookstore.utils.WEBUtils;

public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	OrderService orderService = new OrderServiceImpl();

	protected void orderInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");

		List<OrderItem> list = orderService.getOrderInfo(orderId);

		request.setAttribute("list", list);

		request.getRequestDispatcher("/pages/order/order-info.jsp").forward(
				request, response);

	}

	protected void takeBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		orderService.takeBook(orderId);
		response.sendRedirect(request.getContextPath()
				+ "/client/OrderClientServlet?method=orderList");
	}

	protected void orderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		// 已登录
		List<Order> list = orderService.getOrderListByUserId(user.getId() + "");
		request.setAttribute("list", list);
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request,
				response);
	}

	/**
	 * 用来结账
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkOut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		Cart cart = WEBUtils.getCart(request);
		String orderId;
		orderId = orderService.createOrder(cart, user);
		request.setAttribute("orderId", orderId);
		request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(
				request, response);
	}

}
