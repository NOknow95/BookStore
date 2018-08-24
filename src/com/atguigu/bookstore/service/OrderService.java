package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;

public interface OrderService {
	/**
	 * 根据购物车的信息和用户的信息 生成订单返回订单号
	 * 
	 * @param cart
	 * @param user
	 * @return
	 */
	String createOrder(Cart cart, User user);

	// OrderClientServlet();
	/**
	 * 管理员调用
	 * 
	 * @return
	 */
	List<Order> getOrderList();

	/**
	 * 根据用户Id返回订单信息
	 * 
	 * @return
	 */
	List<Order> getOrderListByUserId(String userId);

	/**
	 * 发货---修改订单帐号为1 --管理员
	 * 
	 * @param orderId
	 */
	void sendBook(String orderId);

	/**
	 * 交易完成--客户
	 * 
	 * @param orderId
	 */
	void takeBook(String orderId);
	
	List<OrderItem> getOrderInfo(String orderId);
}
