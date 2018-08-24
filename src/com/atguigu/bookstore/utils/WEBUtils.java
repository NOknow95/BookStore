package com.atguigu.bookstore.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Cart;

public class WEBUtils {
	public static <T> T param2Bean(HttpServletRequest request, T t) {
		Map map = request.getParameterMap();
		for (Iterator i = map.keySet().iterator(); i.hasNext();) {
			Object obj = i.next();
			System.out.println(obj);// 循环输出key
			Object[] object = (Object[]) map.get(obj);
			System.out.println("--key=" + obj + " value=" + object[0]);
		}
		try {
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static String getPath(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String queryString = req.getQueryString();
		String path = uri + "?" + queryString;
		if (path.contains("&pageNumber")) {
			path = path.substring(0, path.indexOf("&pageNumber"));
		}
		return path;
	}

	public static Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
}
