package cn.com.hunau.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.hunau.service.ArticleService;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.vo.ArticleVo;

/**
 * Servlet implementation class MyConcernServlet
 */
public class MyConcernServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keywords = request.getParameter("keyword");
		String idStr = request.getParameter("user_id");
		int id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
		}

		String pageIndex = request.getParameter("pageIndex");// 获取当前页码隐藏域1
		int currPageNo;
		if (pageIndex == null) {
			currPageNo = 1;
		} else {// 不为空的话，就链接提交给我的当前页码即是用户请求的页码传给我的值

			currPageNo = Integer.parseInt(pageIndex);

		}

		ArticleService articleService = ServiceFactory.buildServiceFactory().createArticleService();
		List<ArticleVo> articleList = articleService.findMyConcernArticle(id, currPageNo, 3, keywords);
		System.out.println(articleList);
//		 * 3.5 将List设置在request中
		request.setAttribute("user_id", id);
		request.setAttribute("currPageNo", currPageNo);
		request.setAttribute("keywords", keywords);
		request.setAttribute("articleList", articleList);
//		 * 4 转向到snacksList.jsp显示li
		request.getRequestDispatcher("myconcern.jsp").forward(request, response);
	}

}
