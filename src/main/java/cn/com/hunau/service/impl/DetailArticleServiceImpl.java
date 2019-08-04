package cn.com.hunau.service.impl;

import cn.com.hunau.dao.ArticleDAO;
import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.po.ArticlePo;
import cn.com.hunau.po.UserPo;
import cn.com.hunau.service.DetailArticleService;
import cn.com.hunau.service.ServiceFactory;
import cn.com.hunau.vo.ArticleVo;
import cn.com.hunau.vo.DetailArticleVo;

public class DetailArticleServiceImpl implements DetailArticleService {
    @Override
    public ArticleVo findArticleByArticle_id(int article_id) {
        ArticleDAO articleDAO = DAOFactory.buildDAOFactory().createArticleDAO();
        ArticlePo articlePo = articleDAO.findArticleByid(article_id);
        /**
         * 将PO转换成Vo
         */
        ArticleVo articleVo = new ArticleVo(articlePo);
        /**
         * 通过userID找到username
         */
        UserDAO userDAO = DAOFactory.buildDAOFactory().createUserDAO();
        UserPo userioByUser_id = userDAO.findUserioByUser_id(articlePo.getUser_id());
        articleVo.setUser_name(userioByUser_id.getUser_name());
        articleVo.setArticle_date(articlePo.getArticle_date());
        articleVo.setArticle_cover(articlePo.getArticle_cover());
        articleVo.setArticle_context(articlePo.getArticle_context());
        articleVo.setArticle_type(articlePo.getArticle_type());
        articleVo.setArticle_title(articlePo.getArticle_title());
        return articleVo;
    }

    @Override
    public boolean addComment(DetailArticleVo detailArticleVo) {
        return false;
    }

    @Override
    public boolean deleteComment(DetailArticleVo detailArticleVo) {
        return false;
    }

    public static void main(String[] args) {
        DetailArticleService service = ServiceFactory.buildServiceFactory().createDetailArticleService();
        ArticleVo article = service.findArticleByArticle_id(17);
        System.out.println(article);
    }
}
