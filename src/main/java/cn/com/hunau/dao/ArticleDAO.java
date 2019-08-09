package cn.com.hunau.dao;

import cn.com.hunau.po.ArticlePo;

import java.util.List;

public interface ArticleDAO {
    /**
     * 查询我关注的人的所有文章，文章权限为0，按时间排序
     *
     * @param user_id    我的用户id
     * @param currPageNo 当前页面
     * @param number     每页显示条数
     * @return
     */
    public List<ArticlePo> findMyConcernArticle(int user_id, int currPageNo, int number, String keyword);

    /**
     * 查找权限为0的所有文章（按发布时间排序，最新发布的排前面）
     *
     * @param currPageNo 当前页码
     * @param number     每页显示条数
     * @param keyword    搜索关键字
     * @return
     */
    public List<ArticlePo> searchAllArticle(int currPageNo, int number, String keyword);

    /**
     * 查询自己的所有文章（按发布时间排序，最新发布的排前面）
     *
     * @param user_id    本人用户id
     * @param currPageNo 当前页码
     * @param number     每页显示条数
     * @return
     */
    public List<ArticlePo> searchMyArticle(int user_id, int currPageNo, int number);

    /**
     * 查询他人的所有文章（按发布时间排序，最新发布的排前面）
     *
     * @param user_id
     * @param currPageNo
     * @param number
     * @return
     */
    public List<ArticlePo> searchOthersArticle(int user_id, int currPageNo, int number);

    /**
     * 根据文章id查找文章全部信息
     *
     * @param article_id 文章id
     * @return
     */
    public ArticlePo findArticleByid(int article_id);

    /**
     * 添加文章
     *
     * @param article 通过文章Po传值
     * @return
     */
    public int addArticle(ArticlePo article);

    /**
     * 查询浏览量排名为前top文章
     *
     * @param top 排名前top
     * @return
     */
    public List<ArticlePo> searchTopArticle(int top);

    /**
     * 根据文章id删除文章
     *
     * @param article_id
     * @return
     */
    public boolean deleteArticle(int article_id);

    /**
     * 根据文章id修改文章浏览量
     *
     * @param article_id 文章id
     * @param viewCount  文章需要改成的浏览量
     * @return
     */
    public boolean updateArticleViewCount(int article_id, int viewCount);

    /**
     * 查找文章总数
     *
     * @return
     */
    public int findArticleCount();

    /**
     * 查询网站总浏览量
     *
     * @return
     */
    public int findWebViewCount();


}
