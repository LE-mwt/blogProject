package cn.com.hunau.servlet;

import cn.com.hunau.po.CommentPo;
import cn.com.hunau.service.DetailArticleService;
import cn.com.hunau.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddCommentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("comment");
        String parent_id = req.getParameter("parent_id");
        String article_id = req.getParameter("article_id");
        String user_id = req.getParameter("user_id");
        int u_id = Integer.parseInt(user_id);
        int a_id = Integer.parseInt(article_id);
        int p_id = 0;
        if (parent_id != null) {
            p_id = Integer.parseInt(parent_id);
        }
        System.out.println(article_id);
        CommentPo commentPo = new CommentPo();
        commentPo.setUser_id(u_id);
        commentPo.setCom_text(comment);
        commentPo.setArticle_id(a_id);
        commentPo.setCom_parentid(p_id);
        DetailArticleService detailArticleService = ServiceFactory.buildServiceFactory().createDetailArticleService();
        boolean flag = detailArticleService.addComment(commentPo);
        PrintWriter out = resp.getWriter();
        out.print(flag);
    }
}
