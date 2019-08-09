package cn.com.hunau.servlet;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.po.UserPo;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdatePersonServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_id = req.getParameter("user_id");
        int u_id = Integer.parseInt(user_id);
        //更新头像
        UserService userService = ServiceFactory.buildServiceFactory().createUserService();
        if (req.getParameter("picture_path") != null) {
            String picture_path = req.getParameter("picture_path");
            boolean flag = userService.changeUserPicture(u_id, picture_path);
            if (flag) {
                PrintWriter out = resp.getWriter();
                out.print(flag);
            }
        }
        //更新信息
        if (req.getParameter("sex") != null) {
            UserPo userPo = new UserPo();
            String sex = req.getParameter("sex");
            String user_name = req.getParameter("user_name");
            String user_address = req.getParameter("user_address");
            String user_signature = req.getParameter("user_signature");
            System.out.println(user_address);
            if (sex.equals("man")) {
                userPo.setUser_sex("男");
            } else {
                userPo.setUser_sex("女");
            }
            userPo.setUser_address(user_address);
            userPo.setUser_name(user_name);
            userPo.setUser_signature(user_signature);
            userPo.setUser_id(u_id);
            UserDAO userDAO = DAOFactory.buildDAOFactory().createUserDAO();
            boolean flag = userDAO.updateUserio(userPo);
            if (flag) {
                PrintWriter out = resp.getWriter();
                out.print(flag);
            }
        }
    }
}
