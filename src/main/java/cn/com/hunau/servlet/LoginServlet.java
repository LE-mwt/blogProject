package cn.com.hunau.servlet;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.po.UserPo;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user_id = request.getParameter("user_id");
        String user_password = request.getParameter("user_password");
        String yz = request.getParameter("yz");
        String checkbox = request.getParameter("checkbox");

        String code = (String) request.getSession().getAttribute("code");

        UserDAO userdao = DAOFactory.buildDAOFactory().createUserDAO();
        int userid = -1;
        ;
        try {
            userid = Integer.parseInt(user_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getMessage();
        }
        ;

        UserPo userPo = userdao.findUserioByUser_id(userid);
        boolean bool = userdao.checkUser(userid, user_password);
        boolean userexit = userdao.checkUser_id(userid);
        boolean cs = userdao.checkUserState(userid);
        boolean ho = userdao.checkUserHasOnline(userid);


        user_id = String.valueOf(userid);
        String admin = user_id.substring(0, 2);

        boolean flag = yz.equals(code);
        if (cs == true) {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('该用户已被冻结' );window.location.href='Login.jsp'</script>");
            out.flush();
            out.close();
        } else if (ho == true) {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('您的账号已在另一处登录' );window.location.href='Login.jsp'</script>");
            out.flush();
            out.close();

        } else {

            if (bool == true && flag == true) {
                boolean a = userdao.updateOnlineUser_states(userid);

                request.getSession().setAttribute("user_id", userid);
                request.getSession().setAttribute("user_name", userPo.getUser_name());
                if (checkbox != null && !(checkbox.equals(""))) {
                    Cookie cookie = new Cookie("user_id", user_id);
                    cookie.setMaxAge(60 * 60);
                    response.addCookie(cookie);

                    request.setAttribute("user_id", user_id);
                }
                if (admin.equals("88")) {
                    request.getRequestDispatcher("/adminIndexServlet").forward(request, response);
                } else {
                    request.getRequestDispatcher("/indexServlet").forward(request, response);
                }
            } else if (userexit == false) {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('账号不存在，请重新登录' );window.location.href='Login.jsp'</script>");
                out.flush();
                out.close();

            } else if (flag == false) {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('验证码错误，请重新登录' );window.location.href='Login.jsp'</script>");
                out.flush();
                out.close();

            } else {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('密码错误，请重新登录' );window.location.href='Login.jsp'</script>");
                out.flush();
                out.close();

            }


        }

    }

}
