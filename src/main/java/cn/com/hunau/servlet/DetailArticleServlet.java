package cn.com.hunau.servlet;

import cn.com.hunau.service.DetailArticleService;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.vo.DetailArticleVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailArticleServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("article_id");
        System.out.println(id);
        int article_id = Integer.parseInt(id);
        DetailArticleService articleService = ServiceFactory.buildServiceFactory().createDetailArticleService();
        DetailArticleVo article = articleService.findArticleByArticle_id(article_id);
        System.out.println("-----------------" + article.getArticle_id());
        req.setAttribute("article", article);
        req.getRequestDispatcher("/detailArticle.jsp").forward(req, resp);
    }
}
