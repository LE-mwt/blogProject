package cn.com.hunau.service.impl;

import cn.com.hunau.dao.ArticleDAO;
import cn.com.hunau.dao.CommentDAO;
import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.po.ArticlePo;
import cn.com.hunau.po.CommentPo;
import cn.com.hunau.po.UserPo;
import cn.com.hunau.service.DetailArticleService;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.vo.DetailArticleVo;

import java.util.List;

public class DetailArticleServiceImpl implements DetailArticleService {
    @Override
    public DetailArticleVo findArticleByArticle_id(int article_id) {
        ArticleDAO articleDAO = DAOFactory.buildDAOFactory().createArticleDAO();
        ArticlePo articlePo = articleDAO.findArticleByid(article_id);
        /**
         * 将PO转换成Vo
         */
        DetailArticleVo articleVo = new DetailArticleVo(articlePo);
        /**
         * 通过userID找到username
         */
        UserDAO userDAO = DAOFactory.buildDAOFactory().createUserDAO();
        UserPo userPo = userDAO.findUserioByUser_id(articlePo.getUser_id());
        articleVo.setUser(userPo);
        articleVo.setArticle_id(article_id);
        articleVo.setArticle_date(articlePo.getArticle_date());
        articleVo.setArticle_cover(articlePo.getArticle_cover());
        articleVo.setArticle_context(articlePo.getArticle_context());
        articleVo.setArticle_type(articlePo.getArticle_type());
        articleVo.setArticle_title(articlePo.getArticle_title());
        boolean flag = articleDAO.updateArticleViewCount(article_id, articlePo.getArticle_viewcount() + 1);
        if (flag) {
            ArticlePo po = articleDAO.findArticleByid(article_id);
            articleVo.setArticle_viewcount(po.getArticle_viewcount());
        } else {
            articleVo.setArticle_viewcount(articlePo.getArticle_viewcount());
        }
        /**
         * 文章的评论
         */
        CommentDAO commentDAO = DAOFactory.buildDAOFactory().createCommentDAO();
        List<CommentPo> articleComments = commentDAO.findAllCommentsByArticle(article_id);
        articleVo.setComments(articleComments);
        return articleVo;
    }

    @Override
    public boolean addComment(CommentPo commentPo) {
        CommentDAO commentDAO = DAOFactory.buildDAOFactory().createCommentDAO();
        boolean flag = commentDAO.addComments(commentPo);
        return flag;
    }

    @Override
    public boolean deleteComment(int com_id) {
        CommentDAO commentDAO = DAOFactory.buildDAOFactory().createCommentDAO();
        boolean flag = commentDAO.deleteComment(com_id);
        return flag;
    }

    public static void main(String[] args) {
        DetailArticleService service = ServiceFactory.buildServiceFactory().createDetailArticleService();
        DetailArticleVo article = service.findArticleByArticle_id(26);
        System.out.println(article.getComments());
    }
}
