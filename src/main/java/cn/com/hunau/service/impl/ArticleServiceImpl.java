package cn.com.hunau.service.impl;

import cn.com.hunau.dao.ArticleDAO;
import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.po.ArticlePo;
import cn.com.hunau.service.ArticleService;
import cn.com.hunau.vo.ArticleVo;
import cn.com.hunau.vo.UserVo;

import java.util.ArrayList;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDAO articleDAO = DAOFactory.buildDAOFactory().createArticleDAO();
    private UserDAO userDAO = DAOFactory.buildDAOFactory().createUserDAO();

    @Override
    public List<ArticleVo> findArticleByViewcount(int topNumber) {
        return null;
    }

    @Override
    public List<ArticleVo> findArticleByTime(int currPage, int number, String keyword) {
        if (currPage <= 0) {
            currPage = 1;
        }
        List<ArticleVo> articleList = null;
        List<ArticlePo> articlePoList = this.articleDAO.searchAllArticle(currPage, number, keyword);

        articleList = new ArrayList<ArticleVo>();
        ArticleVo vo = null;
        // poList转换成voList
        for (ArticlePo po : articlePoList) {
            vo = new ArticleVo();
            String context = "";
            if (po.getArticle_context().length() > 50) {
                context = po.getArticle_context().substring(0, 50) + "...";
            } else {
                context = po.getArticle_context();
            }
            System.out.println(context);
            vo.setArticle_context(context);
            vo.setArticle_cover(po.getArticle_cover());
            vo.setArticle_date(po.getArticle_date());
            vo.setArticle_id(po.getArticle_id());
            vo.setArticle_private(po.getArticle_private());
            vo.setArticle_title(po.getArticle_title());
            vo.setArticle_type(po.getArticle_type());
            vo.setArticle_viewcount(po.getArticle_viewcount());
            vo.setUser_id(po.getUser_id());

            int userId = vo.getUser_id();
            String userName = this.userDAO.findUserioByUser_id(userId).getUser_name();
            System.out.println(userName);
            vo.setUser_name(userName);
            articleList.add(vo);
        }
        return articleList;
    }

    @Override
    public List<ArticleVo> findMyConcernArticle(UserVo userVo) {
        return null;
    }

    @Override
    public List<ArticleVo> findArticleByUser_id(int user_id) {
        return null;
    }

    @Override
    public int addArticleByUser_id(ArticleVo articleVo) {
        ArticleDAO dao = DAOFactory.buildDAOFactory().createArticleDAO();
        int insertId = dao.addArticle(articleVo);
        return insertId;
    }

    @Override
    public boolean deleteArticleByArticle_id(ArticleVo articleVo) {
        return false;
    }
}
