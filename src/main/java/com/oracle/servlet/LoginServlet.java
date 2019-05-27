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
			System.out.println("��¼�ɹ��ˣ�����ֻ� ���ǣ�"+user.getPhone());
			response.getWriter().append("�ɹ���: ").append(user.getPhone());
		}else {
			response.getWriter().append("ʧ���ˣ������µ�¼: ").append("�һ�������Ե��<a href='login.jsp'>�һ�����</a>");
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
