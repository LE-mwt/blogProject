package cn.com.hunau.admin;

import cn.com.hunau.dao.ArticleDAO;
import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AdminIndexServlet
 */
public class AdminIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        ArticleDAO articledao = DAOFactory.buildDAOFactory().createArticleDAO();
        int articlecount = articledao.findArticleCount();
        int articleviewcount = articledao.findWebViewCount();
        UserDAO userdao = DAOFactory.buildDAOFactory().createUserDAO();
        int usercount = userdao.finAllUser().size();
        request.setAttribute("articleviewcount", articleviewcount);
        request.setAttribute("articlecount", articlecount);
        request.setAttribute("usercount", usercount);
        request.getRequestDispatcher("admin_index.jsp").forward(request, response);
    }

}
