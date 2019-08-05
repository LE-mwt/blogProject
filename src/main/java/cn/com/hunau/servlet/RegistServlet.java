package cn.com.hunau.servlet;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String user_name = request.getParameter("user_name");
        String user_password = request.getParameter("user_password");
        UserDAO userdao = DAOFactory.buildDAOFactory().createUserDAO();
        boolean flag = userdao.findUserByUser_name(user_name);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (flag) {
            out.println("<script>alert('用户名已经存在，请重新注册' );window.location.href='regist.html'</script>");
            out.flush();
            out.close();
        } else {
            boolean bool = userdao.addUser(user_name, user_password);
            System.out.println(bool);
            if (bool) {

                out.println("<script>alert('注册成功' );window.location.href='Login.jsp'</script>");
                out.flush();
                out.close();
            } else {
                out.println("<script>alert('注册失败' );");
                out.flush();
                out.close();
            }
        }
    }

}
