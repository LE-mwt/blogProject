package cn.com.hunau.servlet;

import cn.com.hunau.service.DetailArticleService;
import cn.com.hunau.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("com_id");
        int com_id = Integer.parseInt(id);
        DetailArticleService detailArticleService = ServiceFactory.buildServiceFactory().createDetailArticleService();
        boolean flag = detailArticleService.deleteComment(com_id);
        PrintWriter out = resp.getWriter();
        out.print(flag);
    }
}
