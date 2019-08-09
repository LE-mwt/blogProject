package cn.com.hunau.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;

/**
 * Servlet implementation class UpdateUnOnlineServlet
 */
public class UpdateUnOnlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer user_id = (Integer) request.getSession().getAttribute("user_id");
		
		String userid = String.valueOf(user_id);
		
		int int_userid = -1;
		try{ int_userid = user_id.intValue();}catch(Exception e) {}
		if (userid != null && !(userid.equals(""))) {

			UserDAO userdao = DAOFactory.buildDAOFactory().createUserDAO();
			boolean bool = userdao.updateUnOnlineUser_states(int_userid);
			System.out.println(bool);
			System.out.println("更改成功");
		}
	}

}
