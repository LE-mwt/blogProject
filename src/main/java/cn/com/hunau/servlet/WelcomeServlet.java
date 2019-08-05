package cn.com.hunau.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean hasLogined = false;
		String userName = (String) request.getSession().getAttribute("LOGIN_FLAG");
		if (userName == null || "".equals(userName)) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("loginFlag")) {
						userName = cookie.getValue();
						hasLogined = true;
					}
				}
			}
		} else {
			hasLogined = true;
		}
		if (hasLogined) {
		
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>window.location.href='index.html'</script>");
			out.flush();
			out.close();
			out.flush();
			out.close();
		} else {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
