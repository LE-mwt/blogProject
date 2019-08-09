package cn.com.hunau.servlet;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.FansDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConcernServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        int fan_id = Integer.parseInt(req.getParameter("fan_id"));
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        FansDAO fansDAO = DAOFactory.buildDAOFactory().createFansDAO();
        if (code.equals("add")) {
            fansDAO.addFans(user_id, fan_id);
        } else if (code.equals("delete")) {
            fansDAO.reduceFans(user_id, fan_id);
        }
    }
}
