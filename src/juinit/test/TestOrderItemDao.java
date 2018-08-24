package juinit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;

public class TestOrderItemDao {

	OrderItemDao orderItemDao = new OrderItemDaoImpl();

	@Test
	public void testSaveOrderItem() {
		OrderItem orderItem = new OrderItem(null, 15, 13, "booktitle1", "wjw", 50,
				"/static/img/default.jpg", "15222431115339");
		int count = orderItemDao.saveOrderItem(orderItem);
		System.out.println(count);
	}

	@Test
	public void testGetOrderItemListByOrderId() {
		List<OrderItem> list = orderItemDao.getOrderItemListByOrderId("15222431115339");
		System.out.println(list);
	}

}
