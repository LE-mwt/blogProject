package cn.com.hunau.servlet;

import cn.com.hunau.service.ArticleService;
import cn.com.hunau.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("article_id");
        int article_id = Integer.parseInt(id);
        ArticleService articleService = ServiceFactory.buildServiceFactory().createArticleService();
        articleService.deleteArticleByArticle_id(article_id);
    }
}
