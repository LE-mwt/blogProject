package cn.com.hunau.service.impl;

import cn.com.hunau.service.ArticleService;
import cn.com.hunau.vo.ArticleVo;
import cn.com.hunau.vo.UserVo;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    @Override
    public List<ArticleVo> findArticleByViewcount(int topNumber) {
        return null;
    }

    @Override
    public List<ArticleVo> findArticleByTime(int currPage, int number, String keyword) {
        return null;
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
    public boolean addArticleByUser_id(ArticleVo articleVo) {
        return false;
    }

    @Override
    public boolean deleteArticleByArticle_id(ArticleVo articleVo) {
        return false;
    }
}
