package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;

public interface OrderDao {
	int saveOrder(Order order);

	int updateState(String orderId, int state);// 修改订单状态

	List<Order> getOrderList();// 管理员调用

	List<Order> getOrderListByUserId(String userId);// 用户调用

}
