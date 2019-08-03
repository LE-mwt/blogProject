package cn.com.hunau.vo;

import cn.com.hunau.po.ArticlePo;

/**
 * 文章列表
 */
public class ArticleVo extends ArticlePo {
    private ArticlePo articlePo;

    //扩展的属性
    //作者的用户名
    private String user_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public ArticlePo getArticlePo() {
        return articlePo;
    }

    public void setArticlePo(ArticlePo articlePo) {
        this.articlePo = articlePo;
    }
}
