package cn.com.hunau.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		String yz = request.getParameter("yz");
		System.out.println(yz);
		String code=(String) request.getSession().getAttribute("code");
		System.out.println(code);
	    UserDAO userdao= DAOFactory.buildDAOFactory().createUserDAO();
	    boolean bool=userdao.checkUser(user_name, user_password);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		boolean flag=yz.equals(code);
		if (bool==true&&flag==true) {
			request.getSession().setAttribute("user_name", user_name);
			String forwardServlet = "/welcomeServlet";
				// 保存标记就
				request.getSession().setAttribute("LOGIN_FLAG", user_name);
				Cookie cookie = new Cookie("loginFlag", user_name);
				cookie.setMaxAge(60*60);
				response.addCookie(cookie);
				request.setAttribute("user_name", user_name);
				request.getRequestDispatcher(forwardServlet).forward(request, response);
	
		}
		else {
			
			out.println("<script>alert('用户名或密码或验证码不正确，请重新登录' );window.location.href='Login.jsp'</script>"	);
			out.flush();
			out.close();
		
		}
	
	}

}
