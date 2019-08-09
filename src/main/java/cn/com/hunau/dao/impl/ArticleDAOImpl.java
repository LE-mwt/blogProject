package cn.com.hunau.dao.impl;

import cn.com.hunau.dao.ArticleDAO;
import cn.com.hunau.db.DbConnection;
import cn.com.hunau.po.ArticlePo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOImpl implements ArticleDAO {

    @Override
    public List<ArticlePo> findMyConcernArticle(int user_id, int currPageNo, int number, String keyword) {
        List<ArticlePo> articleList = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "select a.article_id,a.article_title,a.article_cover,a.article_type,a.article_private,"
                    + "a.article_context,a.article_date,a.article_viewcount,a.user_id from article a,"
                    + "fans f where f.f_fan_id= ? and f.f_user_id=a.user_id and a.article_private=0";
            // 判断是否有查询条件，如果有，就在sql后添加where语句
            if (keyword != null && !(keyword = keyword.trim()).equals("")) {
                sql += " and (a.article_title like ? or a.article_type like ?) ";
            }
            sql += " order by a.article_date desc limit ?,?";
            System.out.println(sql);

            pstmt = conn.prepareStatement(sql);
            currPageNo--;
            if (keyword != null && !keyword.equals("")) {
                pstmt.setInt(1, user_id);
                pstmt.setString(2, "%" + keyword + "%");
                pstmt.setString(3, "%" + keyword + "%");
                pstmt.setInt(4, currPageNo * number);
                pstmt.setInt(5, number);
            } else {
                pstmt.setInt(1, user_id);
                pstmt.setInt(2, currPageNo * number);
                pstmt.setInt(3, number);
            }
            rs = pstmt.executeQuery();
            if (rs != null) {
                articleList = new ArrayList<ArticlePo>();
                ArticlePo article = null;
                while (rs.next()) {
                    article = new ArticlePo();
                    article.setArticle_context(rs.getString("article_context"));
                    article.setArticle_cover(rs.getString("article_cover"));
                    article.setArticle_date(rs.getTimestamp("article_date"));
                    article.setArticle_id(rs.getInt("article_id"));
                    article.setArticle_private(rs.getInt("article_private"));
                    article.setArticle_title(rs.getString("article_title"));
                    article.setArticle_type(rs.getString("article_type"));
                    article.setArticle_viewcount(rs.getInt("article_viewcount"));
                    article.setUser_id(rs.getInt("user_id"));
                    articleList.add(article);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return articleList;
    }

    @Override
    public int findArticleCount() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        int count = 0;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            con = dbConn.getConnection();
            pstmt = con.prepareStatement("select * from article");

            set = pstmt.executeQuery();

            while (set.next()) {
                count++;
            }

        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            dbConn.close(con, pstmt, set);
        }

        return count;

    }

    @Override
    public int findWebViewCount() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        int count = 0;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            con = dbConn.getConnection();
            pstmt = con.prepareStatement("select sum(article_viewcount) a from article");

            set = pstmt.executeQuery();

            while (set.next()) {
                count = set.getInt("a");
            }

        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            dbConn.close(con, pstmt, set);
        }

        return count;
    }

    @Override
    public List<ArticlePo> searchAllArticle(int currPageNo, int number, String keyword) {
        List<ArticlePo> articleList = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "select * from article where";
            // 判断是否有查询条件，如果有，就在sql后添加where语句
            if (keyword != null && !(keyword = keyword.trim()).equals("")) {
                sql += " article_title like ? or article_type like ? and";
            }
            sql += " article_private=0 order by article_date desc limit ?,?";

            pstmt = conn.prepareStatement(sql);
            currPageNo--;
            // 判断是否有条件，设置占位符信息
            if (keyword != null && !keyword.equals("")) {
                pstmt.setString(1, "%" + keyword + "%");
                pstmt.setString(2, "%" + keyword + "%");
                pstmt.setInt(3, currPageNo * number);
                pstmt.setInt(4, number);
            } else {
                pstmt.setInt(1, currPageNo * number);
                pstmt.setInt(2, number);
            }
            rs = pstmt.executeQuery();
            if (rs != null) {
                articleList = new ArrayList<ArticlePo>();
                ArticlePo article = null;
                while (rs.next()) {
                    article = new ArticlePo();
                    article.setArticle_context(rs.getString("article_context"));
                    article.setArticle_cover(rs.getString("article_cover"));
                    article.setArticle_date(rs.getTimestamp("article_date"));
                    article.setArticle_id(rs.getInt("article_id"));
                    article.setArticle_private(rs.getInt("article_private"));
                    article.setArticle_title(rs.getString("article_title"));
                    article.setArticle_type(rs.getString("article_type"));
                    article.setArticle_viewcount(rs.getInt("article_viewcount"));
                    article.setUser_id(rs.getInt("user_id"));
                    articleList.add(article);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return articleList;
    }

    @Override
    public List<ArticlePo> searchMyArticle(int user_id, int currPageNo, int number) {
        List<ArticlePo> articleList = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "select * from article where user_id=? order by article_date desc limit ?,?";
            pstmt = conn.prepareStatement(sql);
            currPageNo--;
            pstmt.setInt(1, user_id);
            pstmt.setInt(2, currPageNo * number);
            pstmt.setInt(3, number);
            rs = pstmt.executeQuery();
            if (rs != null) {
                articleList = new ArrayList<ArticlePo>();
                ArticlePo article = null;
                while (rs.next()) {
                    article = new ArticlePo();
                    article.setArticle_context(rs.getString("article_context"));
                    article.setArticle_cover(rs.getString("article_cover"));
                    article.setArticle_date(rs.getTimestamp("article_date"));
                    article.setArticle_id(rs.getInt("article_id"));
                    article.setArticle_private(rs.getInt("article_private"));
                    article.setArticle_title(rs.getString("article_title"));
                    article.setArticle_type(rs.getString("article_type"));
                    article.setArticle_viewcount(rs.getInt("article_viewcount"));
                    article.setUser_id(rs.getInt("user_id"));
                    articleList.add(article);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return articleList;
    }

    @Override
    public List<ArticlePo> searchOthersArticle(int user_id, int currPageNo, int number) {
        List<ArticlePo> articleList = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "select * from article where user_id=? and article_private=0 order by article_date desc limit ?,?";
            pstmt = conn.prepareStatement(sql);
            currPageNo--;
            pstmt.setInt(1, user_id);
            pstmt.setInt(2, currPageNo * number);
            pstmt.setInt(3, number);
            rs = pstmt.executeQuery();
            if (rs != null) {
                articleList = new ArrayList<ArticlePo>();
                ArticlePo article = null;
                while (rs.next()) {
                    article = new ArticlePo();
                    article.setArticle_context(rs.getString("article_context"));
                    article.setArticle_cover(rs.getString("article_cover"));
                    article.setArticle_date(rs.getTimestamp("article_date"));
                    article.setArticle_id(rs.getInt("article_id"));
                    article.setArticle_private(rs.getInt("article_private"));
                    article.setArticle_title(rs.getString("article_title"));
                    article.setArticle_type(rs.getString("article_type"));
                    article.setArticle_viewcount(rs.getInt("article_viewcount"));
                    article.setUser_id(rs.getInt("user_id"));
                    articleList.add(article);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return articleList;
    }

    @Override
    public int addArticle(ArticlePo article) {
        boolean flag = false;
        int insertId = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "insert into article(article_title,article_cover,"
                    + "article_type,article_private,article_context,article_date,user_id)"
                    + "values(?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getArticle_title());
            pstmt.setString(2, article.getArticle_cover());
            pstmt.setString(3, article.getArticle_type());
            pstmt.setInt(4, article.getArticle_private());
            pstmt.setString(5, article.getArticle_context());
//            pstmt.setTimestamp(6, article.getArticle_date());
            pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
//			pstmt.setInt(7, article.getArticle_viewcount());
            pstmt.setInt(7, article.getUser_id());
            int count = pstmt.executeUpdate();
            if (count != 0) {
                flag = true;
                insertId = getInsertId(conn);
//                System.out.println("***********************************" + insertId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return insertId;
    }

    public int getInsertId(Connection conn) {
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT LAST_INSERT_ID()";
            pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<ArticlePo> searchTopArticle(int top) {
        List<ArticlePo> articleList = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "select * from article where article_private=0 order by article_viewcount desc limit 0,?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, top);
            rs = pstmt.executeQuery();
            if (rs != null) {
                articleList = new ArrayList<ArticlePo>();
                ArticlePo article = null;
                while (rs.next()) {
                    article = new ArticlePo();
                    article.setArticle_context(rs.getString("article_context"));
                    article.setArticle_cover(rs.getString("article_cover"));
                    article.setArticle_date(rs.getTimestamp("article_date"));
                    article.setArticle_id(rs.getInt("article_id"));
                    article.setArticle_private(rs.getInt("article_private"));
                    article.setArticle_title(rs.getString("article_title"));
                    article.setArticle_type(rs.getString("article_type"));
                    article.setArticle_viewcount(rs.getInt("article_viewcount"));
                    article.setUser_id(rs.getInt("user_id"));
                    articleList.add(article);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return articleList;
    }

    @Override
    public ArticlePo findArticleByid(int article_id) {
        ArticlePo article = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "select * from article where article_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, article_id);
            rs = pstmt.executeQuery();
            if (rs != null && rs.next()) {
                article = new ArticlePo();
                article.setArticle_context(rs.getString("article_context"));
                article.setArticle_cover(rs.getString("article_cover"));
                article.setArticle_date(rs.getTimestamp("article_date"));
                article.setArticle_id(rs.getInt("article_id"));
                article.setArticle_private(rs.getInt("article_private"));
                article.setArticle_title(rs.getString("article_title"));
                article.setArticle_type(rs.getString("article_type"));
                article.setArticle_viewcount(rs.getInt("article_viewcount"));
                article.setUser_id(rs.getInt("user_id"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }

        return article;
    }

    @Override
    public boolean deleteArticle(int article_id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "delete from article where article_id=?";
            if (findArticleByid(article_id) != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, article_id);
                int count = pstmt.executeUpdate();
                if (count != 0) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return flag;
    }

    @Override
    public boolean updateArticleViewCount(int article_id, int viewCount) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "update article set article_viewcount=? where article_id=?";
            if (findArticleByid(article_id) != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, viewCount);
                pstmt.setInt(2, article_id);
                int count = pstmt.executeUpdate();
                if (count != 0) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return flag;

    }
}
