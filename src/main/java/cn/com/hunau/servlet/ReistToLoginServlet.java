package cn.com.hunau.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ReistToLoginServlet
 */
public class ReistToLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        Cookie cookies[] = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {

            if (cookies[i].getName().equals("user_id")) {
                cookies[i].setValue("-1");
            }
            response.addCookie(cookies[i]);
        }

        request.getRequestDispatcher("Login.jsp").forward(request, response);
        //response.sendRedirect("Login.jsp");
    }

}
