package juinit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class TestBookDao {
	BookDao bookDao = new BookDaoImpl();

	@Test
	public void testBatchUpdateSalesAndStock() {
		// sales--stock--id
		Object[][] params = new Object[2][];
		params[0] = new Object[] { 300, 400, 75 };
		params[1] = new Object[] { 500, 500, 76 };
		bookDao.batchUpdateSalesAndStock(params);
	}

	@Test
	public void testSaveBook() {
		Book book = new Book("解忧杂货店111", "东野圭吾", 27.20, 100, 100,
				"/static/img/default.jpg");
		int saveBook = bookDao.saveBook(book);
		System.out.println(saveBook);
	}

	@Test
	public void testDelBook() {
		System.out.println(bookDao.delBook(35 + ""));
		;
	}

	@Test
	public void testUpdateBook() {
		Book book = new Book("解忧杂货店222", "东野圭吾", 27.20, 100, 100,
				"/static/img/default.jpg");
		book.setId(35);
		bookDao.updateBook(book);
	}

	@Test
	public void testGetBookList() {
		List<Book> bookList = bookDao.getBookList();
		System.out.println(bookList.toString());
	}

	@Test
	public void testGetBookById() {
		System.out.println(bookDao.getBookById(1 + ""));
	}

	@Test
	public void testFindBook() {
		Page<Book> page = new Page<Book>();
		page.setPageNumber(3);
		page.setPageSize(4);
		Page<Book> pg = bookDao.findBook(page);
		List<Book> data = pg.getData();
		for (Book book : data) {
			System.out.println(book);
		}
	}

	@Test
	public void testFindBookByPrcie() {
		Page<Book> page = new Page<Book>();
		page.setPageNumber(3);
		page.setPageSize(4);
		Page<Book> pg = bookDao.findBook(page);
		Page<Book> page2 = bookDao.findBookByPrice(page, 10, 20);
		for (Book book : page2.getData()) {
			System.out.println(book);
		}
	}

}
