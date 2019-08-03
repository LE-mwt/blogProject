package cn.com.hunau.po;

import java.sql.Timestamp;

/**
 * 评论的Po类
 */
public class CommentPo {
    //评论的id
    private int com_id;
    //评论的用户的id
    private int user_id;
    //文章id
    private int article_id;
    //评论的时间
    private Timestamp com_time;
    //评论的内容
    private String com_text;
    //根据parentid判断是评论文章还是回复评论
    private int com_parentid;

    public int getCom_parentid() {
        return com_parentid;
    }

    public void setCom_parentid(int com_parentid) {
        this.com_parentid = com_parentid;
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public Timestamp getCom_time() {
        return com_time;
    }

    public void setCom_time(Timestamp com_time) {
        this.com_time = com_time;
    }

    public String getCom_text() {
        return com_text;
    }

    public void setCom_text(String com_text) {
        this.com_text = com_text;
    }

    @Override
    public String toString() {
        return "com_id=" + com_id + " user_id= " + user_id + " article_id= " + article_id + " com_time= " + com_time + " com_text= " + com_text + " parentid= " + com_parentid;
    }
}
