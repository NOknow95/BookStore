package com.atguigu.bookstore.servlet.manager;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.client.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookManagerServlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookServiceImpl();

	/**
	 * 根据分页查找图书
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findBook(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pageNumber = req.getParameter("pageNumber");
		int pageSize = 4;
		Page<Book> page = bookService.findBook(pageNumber, pageSize);

		String path = WEBUtils.getPath(req);
		page.setPath(path);

		req.setAttribute("page", page);
		req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(
				req, resp);
	}

	/**
	 * 修改或添加图书
	 * 
	 * @param request
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateBook(HttpServletRequest request,
			HttpServletResponse resp) throws ServletException, IOException {
		Book book = WEBUtils.param2Bean(request, new Book());
		if (book.getId() == null || book.getId() == 0) {
			bookService.saveBook(book);
		} else {
			bookService.updateBook(book);
		}

		String referer = request.getParameter("referer");
		resp.sendRedirect(referer);
		// resp.sendRedirect(request.getContextPath()
		// + "/manager/BookManagerServlet?method=findBook");
	}

	/**
	 * 去修改图书的页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = bookService.getBookById(bookId);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(
				request, response);
	}

	/**
	 * 删除一本图书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		bookService.delBook(bookId);
		String referer = request.getHeader("Referer");
		System.out.println(referer);
		response.sendRedirect(referer);
	}

	/**
	 * 显示所有的图书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void bookList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Book> list = bookService.getBookList();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp")
				.forward(request, response);
	}

	/**
	 * 向数据库中添加图书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Book book = WEBUtils.param2Bean(request, new Book());
		bookService.saveBook(book);
		request.getRequestDispatcher(
				"/manager/BookManagerServlet?method=bookList").forward(request,
				response);
	}

}
