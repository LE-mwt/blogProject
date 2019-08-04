package cn.com.hunau.vo;

import cn.com.hunau.po.ArticlePo;
import cn.com.hunau.po.CommentPo;
import cn.com.hunau.po.UserPo;

import java.util.List;

/**
 * 文章详情页的vo
 */
public class DetailArticleVo extends ArticlePo {
    private ArticlePo articlePo;
    //文章的评论
    private List<CommentPo> comments;
    //作者的详细信息
    private UserPo user;

    public DetailArticleVo() {
    }

    public DetailArticleVo(ArticlePo articlePo) {
        this.articlePo = articlePo;
    }

    public ArticlePo getArticlePo() {
        return articlePo;
    }

    public void setArticlePo(ArticlePo articlePo) {
        this.articlePo = articlePo;
    }

    public List<CommentPo> getComments() {
        return comments;
    }

    public void setComments(List<CommentPo> comments) {
        this.comments = comments;
    }

    public UserPo getUser() {
        return user;
    }

    public void setUser(UserPo user) {
        this.user = user;
    }
}
