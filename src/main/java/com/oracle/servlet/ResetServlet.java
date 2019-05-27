package com.oracle.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.service.UserService;

/**
 * Servlet implementation class ResetServlet
 */
@WebServlet("/reset.do")
public class ResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String code = request.getParameter("code");
		String password = request.getParameter("password");
		UserService service = new UserService();
		response.setContentType("text/html;charset=UTF-8");
		// 验证码是否有效；
		if (service.isCode(phone, code)) {
			// 重新设置密码；
			service.resetPasswd(userName, password);
			response.getWriter().append("重置成功").append("可以重新<a href='login.jsp'>登录</a>");
		} else {
			response.getWriter().append("验证码不存在: ").append(request.getContextPath());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
