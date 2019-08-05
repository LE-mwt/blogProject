package cn.com.hunau.service;

import cn.com.hunau.po.CommentPo;
import cn.com.hunau.vo.DetailArticleVo;

public interface DetailArticleService {
    /**
     * 找到指定文章的所有信息
     *
     * @param article_id 文章id
     * @return
     */
    DetailArticleVo findArticleByArticle_id(int article_id);


    /**
     * 添加评论
     *
     * @param commentPo
     * @return
     */
    boolean addComment(CommentPo commentPo);

    /**
     * 删除评论
     *
     * @param com_id
     * @return
     */
    boolean deleteComment(int com_id);
}
