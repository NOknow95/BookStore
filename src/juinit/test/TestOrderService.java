package juinit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

public class TestOrderService {

	OrderService orderService = new OrderServiceImpl();

	BookDao bookDao = new BookDaoImpl();

	@Test
	public void testCreateOrder() {
		Cart cart = new Cart();

		Book book1 = bookDao.getBookById("75");
		Book book2 = bookDao.getBookById("76");
		Book book3 = bookDao.getBookById("77");

		cart.addBook2Cart(book1);
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book3);

		User user = new User();
		user.setId(9);

		String orderId = orderService.createOrder(cart, user);

		System.out.println(orderId);

	}

}
