package juinit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

public class TestBookServie {
	BookService bookservice = new BookServiceImpl();

	@Test
	public void testFindBookByPrcie() {

		Page<Book> page = bookservice.findBookByPrice("1", 4, "10", "20");

		for (Book book : page.getData()) {
			System.out.println(book);
		}
	}

}
