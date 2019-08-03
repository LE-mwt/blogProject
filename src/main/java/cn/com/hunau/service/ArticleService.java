package cn.com.hunau.service;

import cn.com.hunau.vo.ArticleVo;
import cn.com.hunau.vo.UserVo;

import java.util.List;

public interface ArticleService {

    /**
     * 首页的热门文章列表
     *
     * @param topNumber
     * @return
     */
    List<ArticleVo> findArticleByViewcount(int topNumber);

    /**
     * 发现页的最新文章列表
     *
     * @param currPage 当前页面
     * @param number   一页多少个
     * @param keyword  关键字
     * @return
     */
    List<ArticleVo> findArticleByTime(int currPage, int number, String keyword);

    /**
     * 我的关注页面的文章列表
     *
     * @param userVo
     * @return
     */
    List<ArticleVo> findMyConcernArticle(UserVo userVo);

    /**
     * 找到一个用户的所有文章
     *
     * @param user_id
     * @return
     */
    List<ArticleVo> findArticleByUser_id(int user_id);

    /**
     * 发布文章
     *
     * @param articleVo 文章vo
     */
    boolean addArticleByUser_id(ArticleVo articleVo);

    /**
     * 删除文章
     *
     * @param articleVo
     * @return
     */
    boolean deleteArticleByArticle_id(ArticleVo articleVo);
}
