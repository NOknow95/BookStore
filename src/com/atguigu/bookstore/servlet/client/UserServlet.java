package com.atguigu.bookstore.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;
import com.atguigu.bookstore.utils.WEBUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

	protected void checkUsername(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		boolean isCanUse = userService.checkUsername(username);
		if (isCanUse) {
			response.getWriter().print("1");
		} else {
			response.getWriter().print("0");
		}
	}

	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		User user = WEBUtils.param2Bean(request, new User());
		User loginUser = userService.login(user);
		if (loginUser == null) {
			request.setAttribute("msg", "用户名或密码错误!");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);
		} else {
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/login_success.jsp");
		}
	}

	protected void regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取用户填写的验证码
		HttpSession session = request.getSession();
		String sessCode = (String) session.getAttribute("code");
		session.removeAttribute("code");

		String reqCode = request.getParameter("code");

		if (reqCode != null
				&& sessCode.toLowerCase().equals(reqCode.toLowerCase())) {
			// 验证码正确
			User user = WEBUtils.param2Bean(request, new User());
			boolean isRegisterOK = userService.register(user);
			if (isRegisterOK) {
				response.sendRedirect(request.getContextPath()
						+ "/pages/user/regist_success.jsp");
			} else {
				request.setAttribute("msg", "用户名已存在");
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(
						request, response);
			}
		} else {
			request.setAttribute("msg", "验证码错误！");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);
		}
	}

	/**
	 * 注销
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void loginOut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("loginOut...");
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
}
