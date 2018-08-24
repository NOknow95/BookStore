package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookDao {
	int saveBook(Book book);

	int delBook(String bookId);

	int updateBook(Book book);

	List<Book> getBookList();

	Book getBookById(String bookId);

	Page<Book> findBook(Page<Book> page);

	Page<Book> findBookByPrice(Page<Book> page, double minPrice, double maxPrice);

	void batchUpdateSalesAndStock(Object[][] params);
}
