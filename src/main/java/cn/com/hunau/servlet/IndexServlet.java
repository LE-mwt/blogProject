package cn.com.hunau.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.hunau.service.ArticleService;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.vo.ArticleVo;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArticleService articleService = ServiceFactory.buildServiceFactory().createArticleService();
		List<ArticleVo> articleList = articleService.searchTopArticle(10);
		List<ArticleVo> slideArticelList = new ArrayList<ArticleVo>();
		List<ArticleVo> moreArticelList = new ArrayList<ArticleVo>();
		for (int i = 0; i < 5; i++) {
			slideArticelList.add(articleList.get(i));
		}
		for (int i = 5; i < 10; i++) {
			moreArticelList.add(articleList.get(i));
		}

		request.setAttribute("slideArticelList", slideArticelList);
		request.setAttribute("moreArticelList", moreArticelList);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
