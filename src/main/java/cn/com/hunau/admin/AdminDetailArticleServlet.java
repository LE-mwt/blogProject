package cn.com.hunau.admin;

import cn.com.hunau.service.DetailArticleService;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.vo.DetailArticleVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AdminDetailAarticleServlet
 */
public class AdminDetailArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("article_id");
//        System.out.println(id);
        int article_id = Integer.parseInt(id);
        DetailArticleService articleService = ServiceFactory.buildServiceFactory().createDetailArticleService();
        DetailArticleVo article = articleService.findArticleByArticle_id(article_id);
//        System.out.println("-----------------" + article.getArticle_id());
        request.setAttribute("article", article);
        request.getRequestDispatcher("adminDetailArticle.jsp").forward(request, response);
    }

}
