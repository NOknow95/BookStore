package com.atguigu.bookstore.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WEBUtils;

/**
 * 处理前端图书显示的请求
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookService bookService = new BookServiceImpl();

	// 前端分页查找图书的方法
	protected void findBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int pageSize =4;
		Page<Book> page = bookService.findBook(pageNumber, pageSize);
		page.setPath(WEBUtils.getPath(request));
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/book-list.jsp").forward(request, response);
	}
	// 前端分页查找图书的方法
	protected void findBookByPrice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int pageSize =4;
		String max= request.getParameter("max");
		String min= request.getParameter("min");
		Page<Book> page = bookService.findBookByPrice(pageNumber, pageSize, min, max);
		page.setPath(WEBUtils.getPath(request));
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/book-list.jsp").forward(request, response);
	}

}
