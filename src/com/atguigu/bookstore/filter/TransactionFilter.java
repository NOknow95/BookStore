package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.utils.JDBCUtils;

public class TransactionFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("1");
		
		Connection conn = JDBCUtils.getConnection();

		System.out.println("2");
		// 设置手动提交事务
		try {
			System.out.println("3");
			conn.setAutoCommit(false);

			chain.doFilter(request, response);
			System.out.println("4");

			conn.commit();

		} catch (Exception e) {
			System.out.println("5");
			e.printStackTrace();
			// 如果有异常回滚事物
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			request.setAttribute("error", "系统出现异常，请联系管理员！");
			request.getRequestDispatcher("/pages/error/error.jsp").forward(
					request, response);

		} finally {
			System.out.println("6");
			JDBCUtils.realseConnection();
		}
	}

}
