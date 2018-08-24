package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable{
	private Book book;// 购买的图书信息
	private int count;// 购买的图书信息

	public CartItem() {
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		BigDecimal price = new BigDecimal(book.getPrice() + "");
		BigDecimal count = new BigDecimal(getCount() + "");

		return price.multiply(count).doubleValue();
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
}
