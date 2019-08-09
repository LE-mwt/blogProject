package cn.com.hunau.servlet;

import cn.com.hunau.service.MessageService;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.vo.MessageVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowMessageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("user_id");
        int user_id = Integer.parseInt(id);
        MessageService messageService = ServiceFactory.buildServiceFactory().createMessageService();
        List<MessageVo> messages = messageService.findMessageByUser(user_id);
        //整体判断是否有新消息
        int count = 0;
        for (MessageVo message : messages
        ) {
//            System.out.println(message.getNewMessage());
            if (message.getNewMessage() != null) {
                count++;
            }
        }
        req.setAttribute("haveNew", count);
        req.setAttribute("messagesList", messages);
        req.getRequestDispatcher("/message.jsp").forward(req, resp);
    }
}
