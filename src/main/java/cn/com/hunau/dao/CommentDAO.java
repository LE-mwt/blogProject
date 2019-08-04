package cn.com.hunau.dao;

import cn.com.hunau.po.CommentPo;

import java.util.List;

public interface CommentDAO {

    /**
     * 找出一篇文章的所有评论
     *
     * @param article_id 文章的id
     * @return
     */
    List<CommentPo> findAllCommentsByArticle(int article_id);

    /**
     * 找到一篇文章中回复指定文章评论的评论
     *
     * @param article_id
     * @param com_id
     * @return
     */
    List<CommentPo> findCommentsByComments(int article_id, int com_id);

    /**
     * 判断该评论是否是该用户的
     *
     * @param user_id
     * @param com_id
     * @return
     */
    boolean findCommentsByUser(int user_id, int com_id);

    /**
     * 添加对文章的评论
     *
     * @param commentPo
     */
    boolean addComments(CommentPo commentPo);

    /**
     * 根据com_id删除评论
     *
     * @param com_id
     */
    void deleteComment(int com_id);
}
