package cn.com.hunau.dao.impl;

import cn.com.hunau.dao.CommentDAO;
import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.db.DbConnection;
import cn.com.hunau.po.CommentPo;
import cn.com.hunau.po.UserPo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {

    @Override
    public List<CommentPo> findAllCommentsByArticle(int article_id) {
        List<CommentPo> list = new ArrayList<CommentPo>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        try {
            conn = DbConnection.getInstance().getConnection();
            String sql = "select * from comments where article_id=? and com_parentid is null";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, article_id);
            result = pstmt.executeQuery();
            if (result != null) {
                while (result.next()) {
                    CommentPo po = new CommentPo();
                    po.setCom_id(result.getInt("com_id"));
                    po.setArticle_id(result.getInt("article_id"));
                    po.setUser_id(result.getInt("user_id"));
                    po.setCom_time(result.getTimestamp("com_time"));
                    po.setCom_text(result.getString("com_text"));
                    List<CommentPo> byComments = findCommentsByComments(article_id, po.getCom_id());
                    po.setComments(byComments);
                    //得到发表该评论的用户的信息
                    UserDAO userDAO = DAOFactory.buildDAOFactory().createUserDAO();
                    UserPo user = userDAO.findUserioByUser_id(po.getUser_id());
                    po.setUser(user);
                    list.add(po);
                    System.out.println(po);
                    System.out.println(po.getUser().getUser_name());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CommentPo> findCommentsByComments(int article_id, int com_id) {
        List<CommentPo> list = new ArrayList<CommentPo>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        conn = DbConnection.getInstance().getConnection();
        String sql = "select * from comments where article_id = ? and com_parentid = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, article_id);
            pstmt.setInt(2, com_id);
            result = pstmt.executeQuery();
            if (result != null) {
                CommentPo po = new CommentPo();
                while (result.next()) {
                    po.setCom_id(result.getInt("com_id"));
                    po.setArticle_id(result.getInt("article_id"));
                    po.setUser_id(result.getInt("user_id"));
                    po.setCom_time(result.getTimestamp("com_time"));
                    po.setCom_text(result.getString("com_text"));
                    po.setCom_parentid(com_id);
                    //得到发表该评论的用户的信息
                    UserDAO userDAO = DAOFactory.buildDAOFactory().createUserDAO();
                    UserPo user = userDAO.findUserioByUser_id(po.getUser_id());
                    po.setUser(user);
                    list.add(po);
                    System.out.println(po.getUser().getUser_name());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CommentPo> findCommentsByUser(int user_id) {
        List<CommentPo> list = new ArrayList<CommentPo>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        conn = DbConnection.getInstance().getConnection();
        String sql = "select * from comments where user_id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            result = pstmt.executeQuery();
            if (result != null) {
                while (result.next()) {
                    CommentPo commentPo = new CommentPo();
                    System.out.println("---------------" + result.getInt("article_id"));
                    commentPo.setArticle_id(result.getInt("article_id"));
                    list.add(commentPo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Timestamp findCommentsByTime(int article_id) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        conn = DbConnection.getInstance().getConnection();
        String sql = "select com_time from comments where article_id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, article_id);
            result = pstmt.executeQuery();
            if (result != null) {
                CommentPo commentPo = new CommentPo();
                while (result.next()) {
                    time = result.getTimestamp("com_time");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return time;
    }

    @Override
    public boolean addComments(CommentPo commentPo) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        conn = DbConnection.getInstance().getConnection();
        String sql = "";
        try {
            if (commentPo.getCom_parentid() != 0) {
                sql = "insert into comments(user_id,article_id,com_time,com_text,com_parentid) values(?,?,?,?,?) ";
            } else {
                sql = "insert into comments(user_id,article_id,com_time,com_text) values(?,?,?,?) ";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, commentPo.getUser_id());
            pstmt.setInt(2, commentPo.getArticle_id());
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setString(4, commentPo.getCom_text());
            if (commentPo.getCom_parentid() != 0) {
                pstmt.setInt(5, commentPo.getCom_parentid());
            }
            int i = pstmt.executeUpdate();
            if (i != 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteComment(int com_id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        conn = DbConnection.getInstance().getConnection();
        String sql = "delete from comments where com_id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, com_id);
            int i = pstmt.executeUpdate();
            if (i != 0) {
//                System.out.println("删除成功");
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
        CommentDAO dao = DAOFactory.buildDAOFactory().createCommentDAO();
//        List<CommentPo> comments = dao.findAllCommentsByArticle(1);
//        for (CommentPo comment : comments
//        ) {
//            System.out.println(comment);
//        }
//        List<CommentPo> commentsByUser = dao.findCommentsByUser(1);
        List<CommentPo> commentsByUser = dao.findCommentsByUser(1);
        for (CommentPo po : commentsByUser
        ) {
            System.out.println(po.getArticle_id());
            Timestamp time = dao.findCommentsByTime(po.getArticle_id());
            System.out.println(time);
        }
    }
}
