package cn.com.hunau.service;

import cn.com.hunau.vo.ArticleVo;
import cn.com.hunau.vo.DetailArticleVo;

public interface DetailArticleService {
    /**
     * 找到指定文章的所有信息
     *
     * @param article_id 文章id
     * @return
     */
    ArticleVo findArticleByArticle_id(int article_id);


    /**
     * 添加评论
     *
     * @param detailArticleVo
     * @return
     */
    boolean addComment(DetailArticleVo detailArticleVo);

    /**
     * 删除评论
     *
     * @param detailArticleVo
     * @return
     */
    boolean deleteComment(DetailArticleVo detailArticleVo);
}
