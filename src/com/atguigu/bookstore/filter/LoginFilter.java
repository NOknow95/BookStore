package com.atguigu.bookstore.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;

public class LoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			// 未登录
			request.setAttribute("msg", "该操作需要用户登录！");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);
		} else {
			// 已登录
			chain.doFilter(request, response);
		}
	}

}
