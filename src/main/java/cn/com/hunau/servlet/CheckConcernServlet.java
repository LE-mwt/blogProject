package cn.com.hunau.servlet;

import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckConcernServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        int fan_id = Integer.parseInt(req.getParameter("fan_id"));
        UserService userService = ServiceFactory.buildServiceFactory().createUserService();
        boolean flag = userService.checkFans(user_id, fan_id);
        PrintWriter out = resp.getWriter();
        out.print(flag);
    }
}
