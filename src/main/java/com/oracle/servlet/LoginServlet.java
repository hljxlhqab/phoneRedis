package com.oracle.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.service.UserService;
import com.oracle.vo.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		UserService userSevice =new UserService();
		
		User user=userSevice.getUser(userName);
		
		response.setContentType("text/html;charset=UTF-8");
		if(user!=null && user.getPassword().equals(password)) {
			System.out.println("登录成功了；你的手机 号是："+user.getPhone());
			response.getWriter().append("成功了: ").append(user.getPhone());
		}else {
			response.getWriter().append("失败了，请重新登录: ").append("找回密码可以点击<a href='login.jsp'>找回密码</a>");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
