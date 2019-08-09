package cn.com.hunau.admin;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CancleDongjieUserServlet
 */
public class CancleDongjieUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user_id = request.getParameter("user_id");
        UserDAO userdao = DAOFactory.buildDAOFactory().createUserDAO();
        boolean flag = userdao.updateUnOnlineUser_states(Integer.parseInt(user_id));
    }

}
