package cn.com.hunau.servlet;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class OutServlet
 */
public class OutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("----------------------------------------------------------");
        Integer user_id = (Integer) request.getSession().getAttribute("user_id");

        String userid = String.valueOf(user_id);

        int int_userid = -1;
        try {
            int_userid = user_id.intValue();
        } catch (Exception e) {
        }
        if (userid != null && !(userid.equals(""))) {

            UserDAO userdao = DAOFactory.buildDAOFactory().createUserDAO();
            boolean bool = userdao.updateUnOnlineUser_states(int_userid);
        }

        request.getSession().invalidate();
        // TODO Auto-generated method stub
        Cookie cookies[] = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {

            if (cookies[i].getName().equals("user_id")) {
                cookies[i].setValue("-1");
            }
            response.addCookie(cookies[i]);
        }

        // request.getRequestDispatcher("Login.jsp").forward(request, response);
        response.sendRedirect("Login.jsp");
    }

}
