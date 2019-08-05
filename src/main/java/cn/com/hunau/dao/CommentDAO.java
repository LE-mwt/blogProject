package cn.com.hunau.dao;

import cn.com.hunau.po.CommentPo;

import java.sql.Timestamp;
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
     * 得到一个用户的所有评论的文章
     *
     * @param user_id
     * @return
     */
    List<CommentPo> findCommentsByUser(int user_id);

    /**
     * 得到一个文章中的评论的最新时间
     *
     * @param article_id
     * @return
     */
    Timestamp findCommentsByTime(int article_id);

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
    boolean deleteComment(int com_id);
}
