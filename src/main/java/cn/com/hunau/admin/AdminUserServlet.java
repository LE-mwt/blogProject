package cn.com.hunau.admin;

import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.service.UserService;
import cn.com.hunau.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class AdminUserServlet
 */
public class AdminUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keywords = request.getParameter("keyword");
        String pageIndex = request.getParameter("pageIndex");// 获取当前页码隐藏域1
        int currPageNo;
        if (pageIndex == null) {
            currPageNo = 1;
        } else {// 不为空的话，就链接提交给我的当前页码即是用户请求的页码传给我的值
            currPageNo = Integer.parseInt(pageIndex);
        }
        UserService userservice = ServiceFactory.buildServiceFactory().createUserService();

        List<UserVo> userlist = userservice.finAllUser(currPageNo, 5, keywords);
//        System.out.println(userlist.toString());
        request.setAttribute("currPageNo", currPageNo);
        request.setAttribute("kewords", keywords);
        request.setAttribute("userlist", userlist);
        request.getRequestDispatcher("adminUser.jsp").forward(request, response);
    }

}
