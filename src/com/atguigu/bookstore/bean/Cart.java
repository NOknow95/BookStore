package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;

public class Cart implements Serializable{
	// 封装购物车信息
	/**
	 * 在map中保存购物项的信息
	 */
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	/**
	 * 总数量
	 */
	private int totalCount;
	/**
	 * 总金额
	 */
	private double totalAmount;

	/**
	 * 修改购物车一个项的数量
	 * 
	 * @param bookId
	 * @param countStr
	 */
	public void updateCount(String bookId, String countStr) {
		int count = 1;
		try {
			count = Integer.parseInt(countStr);
		} catch (Exception e) {
		}
		CartItem cartItem = map.get(bookId);
		cartItem.setCount(count);
	}

	/**
	 * 向购物车添加一个图书
	 * 
	 * @param book
	 */
	public void addBook2Cart(Book book) {
		CartItem cartItem = map.get(book.getId().toString());
		if (cartItem == null) {
			// 添加新图书
			CartItem cartItemNew = new CartItem();
			cartItemNew.setBook(book);
			cartItemNew.setCount(1);
			map.put(book.getId() + "", cartItemNew);
		} else {
			// 购物车中已经有了该购物项，数量+1
			cartItem.setCount(cartItem.getCount() + 1);
		}
	}

	/**
	 * 清空购物车
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * 购物车删除一个图书项
	 * 
	 * @param bookId
	 */
	public void delCartItem(String bookId) {
		map.remove(bookId);
	}

	public List<CartItem> getCartItems() {

		Collection<CartItem> collection = map.values();
		List<CartItem> list = new ArrayList<CartItem>(collection);
		return list;
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public int getTotalCount() {
		int totalCount = 0;
		List<CartItem> list = getCartItems();
		if (list != null) {
			for (CartItem cartItem : list) {
				totalCount += cartItem.getCount();
			}
		}
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public double getTotalAmount() {
		// double totalAmout = 0;
		BigDecimal totalAmout = new BigDecimal("0");
		List<CartItem> list = getCartItems();
		if (list != null) {
			for (CartItem cartItem : list) {
				// totalAmout += cartItem.getAmout();
				BigDecimal amout = new BigDecimal(cartItem.getAmount() + "");
				totalAmout = totalAmout.add(amout);
			}
		}
		return totalAmout.doubleValue();
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
