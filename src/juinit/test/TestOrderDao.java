package juinit.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;

public class TestOrderDao {

	OrderDao orderDao = new OrderDaoImpl();

	@Test
	public void testSaveOrder() {
		String id = System.currentTimeMillis() + "" + 11;
		Order order = new Order(id, new Date(), 10, 33, 0, 11);
		int count = orderDao.saveOrder(order);
		System.out.println(count);
	}

	@Test
	public void testUpdateState() {
		int count = orderDao.updateState("152224432367611", 16);
		System.out.println(count);
	}

	@Test
	public void testGetOrderList() {
		List<Order> orderList = orderDao.getOrderList();
		for (Order order : orderList) {
			System.out.println(order);
		}
	}

	@Test
	public void testGetOrderListByUserId() {
		List<Order> orderList = orderDao.getOrderListByUserId("11");
		for (Order order : orderList) {
			System.out.println(order);
		}
	}
}
