package cn.com.hunau.servlet;

import cn.com.hunau.service.ArticleService;
import cn.com.hunau.service.DetailArticleService;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.vo.ArticleVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddArticleServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleVo articleVo = (ArticleVo) req.getAttribute("articleVo");
        ArticleService articleService = ServiceFactory.buildServiceFactory().createArticleService();
        int article_id = articleService.addArticleByUser_id(articleVo);
        String url = "";
        PrintWriter out = resp.getWriter();
        String msg = "";
        if (article_id != 0) {
            System.out.println("插入成功");
            msg = "发布成功";
            url = "/detailArticle.jsp";

            //获取插入的文章的内容
            DetailArticleService detailArticleService = ServiceFactory.buildServiceFactory().createDetailArticleService();
            ArticleVo article = detailArticleService.findArticleByArticle_id(article_id);
            req.setAttribute("article", article);
        } else {
            System.out.println("插入失败");
            msg = "发布失败";
            url = "/writeArticle.jsp";
        }
//        resp.setCharacterEncoding("utf-8");
        out.println("<script type='text/javascript'>\r\n" +
                "		   alert('" + msg + "');\r\n" +
                "		</script>");
        req.setCharacterEncoding("utf-8");
        req.getRequestDispatcher(url).forward(req, resp);
    }
}
