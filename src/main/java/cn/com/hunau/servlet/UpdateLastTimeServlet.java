package cn.com.hunau.servlet;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateLastTimeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        String lastTime = req.getParameter("lastTime");
        Date date = new Date(lastTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(date);
        Timestamp timestamp = Timestamp.valueOf(d);
//        System.out.println(timestamp);
//        System.out.println(req.getParameter("flag"));
//        if (req.getParameter("flag") == null) {
        UserDAO userDAO = DAOFactory.buildDAOFactory().createUserDAO();
        userDAO.updateUserLastLogin(user_id, timestamp);
//        }
    }
}
