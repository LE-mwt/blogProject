package cn.com.hunau.service.impl;

import cn.com.hunau.service.DetailArticleService;
import cn.com.hunau.vo.ArticleVo;
import cn.com.hunau.vo.DetailArticleVo;

import java.util.List;

public class DetailArticleServiceImpl implements DetailArticleService {
    @Override
    public List<ArticleVo> findArticleByArticle_id(int article_id) {
        return null;
    }

    @Override
    public boolean addComment(DetailArticleVo detailArticleVo) {
        return false;
    }

    @Override
    public boolean deleteComment(DetailArticleVo detailArticleVo) {
        return false;
    }
}
