package com.oracle.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.service.UserService;
import com.oracle.vo.User;

/**
 * Servlet implementation class SendCodeServlet
 */
@WebServlet("/sendCode.do")
public class SendCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String userName=request.getParameter("userName");
		String phone=request.getParameter("phone");
		
		//手机号与userName一致，则发送短信 ； 
		response.setContentType("text/html;charset=UTF-8");
		UserService service=new UserService();
		User user=service.getUser(userName);
		if(user!=null&&user.getPhone().equals(phone)) {
			//发送短信
			service.send(phone);
			response.getWriter().append("<form action='reset.do' method='post'><input type='hidden' name='userName' value='"+userName+"'/> <input type='hidden' name='phone' value='"+phone+"'/>"
					+ "密码<input type='password' name='password' ><br/>验证码<input type='text' name='code' ><br/><input type='submit' value='提交'/></form> ");
		} else {
			response.getWriter().append("手机号或用户名不正确; ").append(request.getContextPath());
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
