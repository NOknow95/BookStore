package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public int saveBook(Book book) {
		String sql = "insert into bs_book (title, author , price , sales , stock , img_path) values(?,?,?,?,?,?)";
		return update(sql, book.getTitle(), book.getAuthor(), book.getPrice(),
				book.getSales(), book.getStock(), book.getImgPath());
	}

	@Override
	public int delBook(String bookId) {
		String sql = "delete from bs_book where id=?";
		return update(sql, bookId);
	}

	@Override
	public int updateBook(Book book) {
		String sql = "update bs_book set title=? , author=? , price=? , sales=? , stock=? , img_path=?"
				+ " where id=?";
		return update(sql, book.getTitle(), book.getAuthor(), book.getPrice(),
				book.getSales(), book.getStock(), book.getImgPath(),
				book.getId());
	}

	@Override
	public List<Book> getBookList() {
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book";
		return getBeanList(sql);
	}

	@Override
	public Book getBookById(String bookId) {
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book where id=?";
		return getBean(sql, bookId);
	}

	@Override
	public Page<Book> findBook(Page<Book> page) {
		/**
		 * 参数中的page从Servlet传来 里头有pageSize 和pageNumber 还需封装两个参数totalRecord和data
		 */
		String sql = "select count(*) from bs_book";
		long totalRecord = (Long) getSingleValue(sql, null);
		page.setTotalRecord((int) totalRecord);

		sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book limit ?,?";
		List<Book> data = getBeanList(sql, page.getIndex(), page.getPageSize());
		page.setData(data);
		return page;
	}

	@Override
	public Page<Book> findBookByPrice(Page<Book> page, double minPrice,
			double maxPrice) {
		String sql = "SELECT count(*) FROM bs_book WHERE price>=? AND price<=?";
		long totalRecord = (Long) getSingleValue(sql, minPrice, maxPrice);
		page.setTotalRecord((int) totalRecord);

		sql = "SELECT id,title,author,price,sales,stock,img_path imgPath"
				+ " FROM bs_book WHERE price>=? AND price<=? LIMIT ?,?";
		List<Book> data = getBeanList(sql, minPrice, maxPrice, page.getIndex(),
				page.getPageSize());
		page.setData(data);
		return page;
	}

	@Override
	public void batchUpdateSalesAndStock(Object[][] params) {
		String sql = "update bs_book set sales=? , stock=? where id=?";
		batchUpdate(sql, params);
	}
}
