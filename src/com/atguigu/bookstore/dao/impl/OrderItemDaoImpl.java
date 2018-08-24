package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements
		com.atguigu.bookstore.dao.OrderItemDao {

	@Override
	public int saveOrderItem(OrderItem orderItem) {
		String sql = "insert into bs_order_item(count,amount,title,author,price,"
				+ "img_path,order_id) values(?,?,?,?,?,?,?)";
		return update(sql, orderItem.getCount(), orderItem.getAmount(),
				orderItem.getTitle(), orderItem.getAuthor(),
				orderItem.getPrice(), orderItem.getImgPath(),
				orderItem.getOrderId());
	}

	@Override
	public List<OrderItem> getOrderItemListByOrderId(String orderId) {
		String sql = "select id,count,amount,title,author,"
				+ "price,img_path imgPath,order_id orderId"
				+ " from bs_order_item where order_id=?";
		return getBeanList(sql, orderId);
	}

	@Override
	public void batchSave(Object[][] params) {
		String sql = "insert into bs_order_item(count,amount,title,author,price,"
				+ "img_path,order_id) values(?,?,?,?,?,?,?)";
		batchUpdate(sql, params);
	}

}
