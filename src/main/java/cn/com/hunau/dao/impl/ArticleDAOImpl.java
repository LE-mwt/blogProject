package cn.com.hunau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.hunau.dao.ArticleDAO;
import cn.com.hunau.db.DbConnection;
import cn.com.hunau.po.ArticlePo;

public class ArticleDAOImpl implements ArticleDAO {

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
				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
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
	public boolean addArticle(ArticlePo article) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbConnection dbConn = DbConnection.getInstance();
		try {
			conn = dbConn.getConnection();
			String sql = "insert into article(article_title,article_cover,"
					+ "article_type,article_private,article_context,article_date,article_viewcount,user_id)"
					+ "values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, article.getArticle_title());
			pstmt.setString(2, article.getArticle_cover());
			pstmt.setString(3, article.getArticle_type());
			pstmt.setInt(4, article.getArticle_private());
			pstmt.setString(5, article.getArticle_context());
			pstmt.setTimestamp(6, article.getArticle_date());
			pstmt.setInt(7, article.getArticle_viewcount());
			pstmt.setInt(8, article.getUser_id());
			int count = pstmt.executeUpdate();
			if (count != 0) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return flag;
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
