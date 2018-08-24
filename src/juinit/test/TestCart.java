package juinit.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;

public class TestCart {
	@Test
	public void testDouble2() {
		//使用String型号构造器
		BigDecimal a = new BigDecimal("0.05");
		BigDecimal b = new BigDecimal("0.01");
		double c = a.add(b).doubleValue();
		System.out.println(c);
	}

	@Test
	public void testDouble() {
		float a = 0.05f;
		float b = 0.01f;
		System.out.println(a + b);
	}

	@Test
	public void testInt() {
		BigDecimal a = new BigDecimal(1);
		for (int i = 1; i <= 10; i++) {
			BigDecimal b = new BigDecimal(i+"");
			a=a.multiply(b);
		}
		System.out.println(a);
	}

	@Test
	public void test() {
		Cart cart = new Cart();
		Book book1 = new Book(1, "book1", "作者1", 15, 5, 2, "");
		Book book2 = new Book(2, "book2", "作者2", 10, 5, 2, "");
		Book book3 = new Book(3, "book3", "作者3", 25, 5, 2, "");
		cart.addBook2Cart(book1);
		cart.addBook2Cart(book1);
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book3);

		System.out.println("总数量：" + cart.getTotalCount());
		System.out.println("总金额：" + cart.getTotalAmount());

		for (CartItem cartItem : cart.getCartItems()) {
			System.out.println(cartItem);
		}

		cart.clear();

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("总数量：" + cart.getTotalCount());
		System.out.println("总金额：" + cart.getTotalAmount());
		for (CartItem cartItem : cart.getCartItems()) {
			System.out.println(cartItem);
		}
	}

}
