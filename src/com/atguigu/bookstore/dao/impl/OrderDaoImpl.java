package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

	// CREATE TABLE bs_order(
	//
	// id VARCHAR(100) PRIMARY KEY,
	// order_time DATETIME ,
	// total_count INT(11) ,
	// total_amount DOUBLE(11,2) ,
	// state INT(2) ,
	// user_id INT(11) ,
	// FOREIGN KEY (user_id) REFERENCES bs_user(id)
	// )
	@Override
	public int saveOrder(Order order) {
		String sql = "insert into bs_order(id,order_time,total_count,"
				+ "total_amount,state,user_id)" + "values(?,?,?,?,?,?)";
		return this.update(sql, order.getId(), order.getOrderTime(),
				order.getTotalCount(), order.getTotalAmount(), order.getState(),
				order.getUserId());
	}

	@Override
	public int updateState(String orderId, int state) {
		String sql = "update bs_order set state=? where id=?";
		return update(sql, state, orderId);
	}

	@Override
	public List<Order> getOrderList() {
		String sql = "select id,order_time orderTime,total_count totalCount,"
				+ "total_amount totalAmount,state,user_id userId from bs_order order by order_time desc";
		return getBeanList(sql);
	}

	@Override
	public List<Order> getOrderListByUserId(String userId) {
		String sql = "select id,order_time orderTime,total_count totalCount,"
				+ "total_amount totalAmount,state,user_id userId from bs_order"
				+ " where user_id=? order by order_time desc";
		return getBeanList(sql, userId);
	}

}
