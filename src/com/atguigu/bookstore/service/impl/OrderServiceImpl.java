package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
	OrderDao orderDao = new OrderDaoImpl();
	OrderItemDao orderItemDao = new OrderItemDaoImpl();
	BookDao bookDao = new BookDaoImpl();

	@Override
	public String createOrder(Cart cart, User user) {
		// 订单号
		String orderId = System.currentTimeMillis() + "" + user.getId();

		double totalAmount = cart.getTotalAmount();

		int totalCount = cart.getTotalCount();

		Order order = new Order(orderId, new Date(), totalCount, totalAmount,
				0, user.getId());

		orderDao.saveOrder(order);

		List<CartItem> cartItems = cart.getCartItems();

		// 创建两个二维数组用来保存book和orderItem参数
		Object[][] itemParams = new Object[cartItems.size()][];
		Object[][] bookParams = new Object[cartItems.size()][];

		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			Book book = cartItem.getBook();
			double amount = cartItem.getAmount();
			int count = cartItem.getCount();

			String title = book.getTitle();
			String author = book.getAuthor();
			double price = book.getPrice();
			String imgPath = book.getImgPath();
			int sales = book.getSales();
			int stock = book.getStock();

			// OrderItem orderItem = new OrderItem(null, count, amount, title,
			// author, price, imgPath, orderId);
			// orderItemDao.saveOrderItem(orderItem);

			// count,amount,title,author,price,img_path,order_id

			itemParams[i] = new Object[] { count, amount, title, author, price,
					imgPath, orderId };
			bookParams[i] = new Object[] { sales + count, stock - count,
					book.getId() };


			// 修改图书的销量及库存
			// book.setSales(sales + count);
			// book.setStock(stock - count);

			// bookDao.updateBook(book);
		}

		// 批量插入
		orderItemDao.batchSave(itemParams);
		bookDao.batchUpdateSalesAndStock(bookParams);

		// 清空购物车
		cart.clear();

		return orderId;
	}

	@Override
	public List<Order> getOrderList() {
		return orderDao.getOrderList();
	}

	@Override
	public List<Order> getOrderListByUserId(String userId) {
		return orderDao.getOrderListByUserId(userId);
	}

	@Override
	public void sendBook(String orderId) {
		orderDao.updateState(orderId, 1);
	}

	@Override
	public void takeBook(String orderId) {
		orderDao.updateState(orderId, 2);
	}

	@Override
	public List<OrderItem> getOrderInfo(String orderId) {
		return orderItemDao.getOrderItemListByOrderId(orderId);
	}

}
