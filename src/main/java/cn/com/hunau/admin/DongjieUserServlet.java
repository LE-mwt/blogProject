package cn.com.hunau.admin;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class DongjieUserServlet
 */
public class DongjieUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String OneId = request.getParameter("user_id");
        String arrs = request.getParameter("arr");

        if (OneId != null) {
            int user_id = Integer.parseInt(OneId);
            UserDAO userdao = DAOFactory.buildDAOFactory().createUserDAO();
            boolean flag = userdao.updateFreezeUser_states(user_id);
            if (flag) {
                PrintWriter out = response.getWriter();
                out.print(flag);
            }

        } else {
            System.out.println(arrs);
            String[] str = arrs.split(",");
            int[] ids = new int[str.length];
            if (str.length > 0) {
                for (int i = 0; i < str.length; i++) {
                    ids[i] = Integer.parseInt(str[i]);
                }
            }
            boolean flag = false;
            for (int id : ids) {
                System.out.println(id);
                UserDAO userdao = DAOFactory.buildDAOFactory().createUserDAO();
                flag = userdao.updateFreezeUser_states(id);
                if (flag = false) {
                    break;
                }
            }
            if (flag) {
                PrintWriter out = response.getWriter();
                out.print(flag);
            }

        }

    }

}
