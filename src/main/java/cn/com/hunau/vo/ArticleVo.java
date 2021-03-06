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

    public ArticleVo() {
    }

    public ArticleVo(ArticlePo articlePo) {
        this.articlePo = articlePo;
    }

    public ArticlePo getArticlePo() {
        return articlePo;
    }

    public void setArticlePo(ArticlePo articlePo) {
        this.articlePo = articlePo;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

//    @Override
//    public int hashCode() {
//        //修改hashcode
//        return this.getArticlePo().hashCode();
//    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null || !(obj instanceof ArticlePo)) {
//            return false;
//        }
//        ArticleVo m = (ArticleVo) obj;//向下转型
//        //id相同则任务是同一个对象
//        return this.getArticlePo().equals(m.getArticlePo());
//    }
}
