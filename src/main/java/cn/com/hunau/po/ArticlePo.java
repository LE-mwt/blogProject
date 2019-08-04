package cn.com.hunau.po;

import java.sql.Timestamp;

public class ArticlePo {
    //文章id
    private int article_id;
    //标题
    private String article_title;
    //封面
    private String article_cover;
    //分类
    private String article_type;
    //是否仅自己可见  0为否
    private int article_private;
    //文章内容
    private String article_context;
    //发布日期
    private Timestamp article_date;
    //文章浏览量
    private int article_viewcount;
    //作者id
    private int user_id;

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_cover() {
        return article_cover;
    }

    public void setArticle_cover(String article_cover) {
        this.article_cover = article_cover;
    }

    public String getArticle_type() {
        return article_type;
    }

    public void setArticle_type(String article_type) {
        this.article_type = article_type;
    }

    public int getArticle_private() {
        return article_private;
    }

    public void setArticle_private(int article_private) {
        this.article_private = article_private;
    }

    public String getArticle_context() {
        return article_context;
    }

    public void setArticle_context(String article_context) {
        this.article_context = article_context;
    }

    public Timestamp getArticle_date() {
        return article_date;
    }

    public void setArticle_date(Timestamp article_date) {
        this.article_date = article_date;
    }

    public int getArticle_viewcount() {
        return article_viewcount;
    }

    public void setArticle_viewcount(int article_viewcount) {
        this.article_viewcount = article_viewcount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return " article_id= " + article_id + " article_title= " + article_title + " article_cover= " + article_cover +
                " article_type= " + article_type + " article_context= " + article_context + "article_private= " + article_private +
                " user_id= " + user_id;
    }
}
