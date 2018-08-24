package com.atguigu.bookstore.servlet.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WEBUtils;
import com.google.gson.Gson;

/**
 * 处理购物车相关请求的Servlet
 * 
 * @author NOknow
 * 
 */
public class CartServlet extends BaseServlet implements Servlet {

	BookService bookService = new BookServiceImpl();

	protected void updateCount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String countStr = request.getParameter("count");
		Cart cart = WEBUtils.getCart(request);
		cart.updateCount(bookId, countStr);

		Map<String, CartItem> map = cart.getMap();
		CartItem cartItem = map.get(bookId);
		
		double totalAmount = cart.getTotalAmount();
		int totalCount = cart.getTotalCount();
		double amount = cartItem.getAmount();
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("totalAmount", totalAmount+"");
		jsonMap.put("totalCount", totalCount+"");
		jsonMap.put("amount", amount+"");
		String json = new Gson().toJson(jsonMap);
		
		response.getWriter().print(json);
	}

	protected void add2Cart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
//		HttpSession session = request.getSession();

		// 获取图书id
		String bookId = request.getParameter("bookId");
		Book book = bookService.getBookById(bookId);

		// Cart cart = (Cart) session.getAttribute("cart");
		// if (cart == null) {
		// cart = new Cart();
		// session.setAttribute("cart", cart);
		// }

		Cart cart = WEBUtils.getCart(request);

		cart.addBook2Cart(book);

		int totalCount = cart.getTotalCount();

		String title = book.getTitle();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("totalCount", totalCount);
		map.put("title", title);

		String json = new Gson().toJson(map);

		response.getWriter().print(json);

		// session.setAttribute("title", book.getTitle());
		//
		// String referer = request.getHeader("referer");
		//
		// if (referer == null) {
		// response.sendRedirect(request.getContextPath() + "/index.jsp");
		// } else {
		// response.sendRedirect(referer);
		// }
	}

	/**
	 * 清空购物车
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cart cart = WEBUtils.getCart(request);
		cart.clear();
		response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
	}

	/**
	 * 删除指定的购物项
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delCartItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Cart cart = WEBUtils.getCart(request);
		cart.delCartItem(bookId);
		response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
	}
}
