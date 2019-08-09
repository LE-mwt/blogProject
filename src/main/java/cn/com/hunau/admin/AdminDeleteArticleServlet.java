package cn.com.hunau.admin;

import cn.com.hunau.service.ArticleService;
import cn.com.hunau.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AdminDeleteArticleServlet
 */
public class AdminDeleteArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String OneId = request.getParameter("article_id");
        String arrs = request.getParameter("arr");
        String de_article_id = request.getParameter("de_article_id");

        if (OneId != null) {
            int article_id = Integer.parseInt(OneId);
            ArticleService articleService = ServiceFactory.buildServiceFactory().createArticleService();
            boolean flag = articleService.deleteArticleByArticle_id(article_id);
            PrintWriter out = response.getWriter();
            out.print(flag);

        } else if (arrs != null) {
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
                ArticleService articleService = ServiceFactory.buildServiceFactory().createArticleService();
                flag = articleService.deleteArticleByArticle_id(id);
                if (flag = false) {
                    break;
                }
            }
            PrintWriter out = response.getWriter();
            out.print(flag);

        } else if (de_article_id != null) {
            int article_id = Integer.parseInt(de_article_id);
            ArticleService articleService = ServiceFactory.buildServiceFactory().createArticleService();
            boolean flag = articleService.deleteArticleByArticle_id(article_id);
            request.getRequestDispatcher("adminArticleServlet").forward(request, response);
        }

    }

}
