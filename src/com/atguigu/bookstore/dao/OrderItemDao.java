package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;

public interface OrderItemDao {
	int saveOrderItem(OrderItem orderItem);

	List<OrderItem> getOrderItemListByOrderId(String orderId);
	
	void batchSave(Object[][] params);
}
