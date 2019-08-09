package cn.com.hunau.servlet;

import cn.com.hunau.service.ArticleService;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.service.UserService;
import cn.com.hunau.vo.ArticleVo;
import cn.com.hunau.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PersonCenterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("user_id");
        int user_id = Integer.parseInt(id);
        UserService userService = ServiceFactory.buildServiceFactory().createUserService();
        UserVo userVo = userService.findUserByid(user_id);
        ArticleService articleService = ServiceFactory.buildServiceFactory().createArticleService();
        List<ArticleVo> myArticles = articleService.searchMyArticle(user_id);
        for (ArticleVo article : myArticles
        ) {
            System.out.println(article.getArticlePo().getArticle_id());
        }
        req.setAttribute("userVo", userVo);
        req.setAttribute("myArticles", myArticles);
        req.getRequestDispatcher("/personcenter.jsp").forward(req, resp);
    }
}
