package juinit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public class TestPage {

	@Test
	public void testGetTotalPage() {
		Page<Book> page = new Page<Book>();
		page.setPageNumber(4);
		page.setPageSize(3);
		page.setTotalRecord(10);
		System.out.println("总页数："+page.getTotalPage());
		System.out.println("当前索引："+page.getIndex());
	}

	@Test
	public void testGetIndex() {
	}

}
