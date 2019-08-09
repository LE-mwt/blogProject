package cn.com.hunau.dao.impl;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.db.DbConnection;
import cn.com.hunau.po.UserPo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    public UserPo findUserioByUser_name(String user_name) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        int count = 0;
        boolean flag = false;
        UserPo userpo = new UserPo();
        DbConnection dbConn = DbConnection.getInstance();
        try {
            con = dbConn.getConnection();
            pstmt = con.prepareStatement("select * from user where user_name = ?");
            pstmt.setString(1, user_name);
            set = pstmt.executeQuery();

            while (set.next()) {
                userpo.setUser_id(set.getInt("user_id"));
                userpo.setUser_name(set.getString("user_name"));
                userpo.setUser_password(set.getString("user_password"));
                userpo.setUser_picture(set.getString("user_picture"));
                userpo.setUser_sex(set.getString("user_sex"));
                userpo.setUser_address(set.getString("user_address"));
                userpo.setUser_signature(set.getString("user_signature"));
                userpo.setStates(set.getInt("user_states"));
            }

        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            dbConn.close(con, pstmt, set);
        }

        return userpo;

    }

    public UserPo findUserioByUser_id(int user_id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        int count = 0;
        boolean flag = false;
        UserPo userpo = new UserPo();
        DbConnection dbConn = DbConnection.getInstance();
        try {
            con = dbConn.getConnection();
            pstmt = con.prepareStatement("select * from user where user_id = ?");
            pstmt.setInt(1, user_id);
            set = pstmt.executeQuery();
            while (set.next()) {
                userpo.setUser_id(set.getInt("user_id"));
                userpo.setUser_name(set.getString("user_name"));
                userpo.setUser_password(set.getString("user_password"));
                userpo.setUser_picture(set.getString("user_picture"));
                userpo.setUser_sex(set.getString("user_sex"));
                userpo.setUser_address(set.getString("user_address"));
                userpo.setUser_signature(set.getString("user_signature"));
                userpo.setStates(set.getInt("user_states"));
                userpo.setUser_lastLogin(set.getTimestamp("user_lastLogin"));
            }

        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            dbConn.close(con, pstmt, set);
        }

        return userpo;

    }

    @Override
    public boolean checkUser(int user_id, String user_password) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        int count = 0;
        boolean flag = false;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            con = dbConn.getConnection();
            pstmt = con.prepareStatement("select * from user where user_id = ? and user_password = ?");
            pstmt.setInt(1, user_id);
            pstmt.setString(2, user_password);
            set = pstmt.executeQuery();
            while (set.next()) {
                count++;
            }
            if (count != 0) {
                flag = true;
            }

        } catch (SQLException e) {

        }

        return flag;
    }

    @Override
    public String addUser(String user_name, String user_password) {
        // TODO Auto-generated method stub

        Connection con = null;
        PreparedStatement pstmt = null;
        con = DbConnection.getInstance().getConnection();
        String id = null;
        try {
            long time = new Date().getTime();
            id = String.valueOf(time).substring(4);
            pstmt = con.prepareStatement("insert into user(user_name,user_password,user_id) values(?,?,?)");
            pstmt.setString(1, user_name);
            pstmt.setString(2, user_password);
//            pstmt.setString(3, id);
            pstmt.setInt(3, Integer.parseInt(id));
            int count = pstmt.executeUpdate();

            if (count == 0) {
                id = "lose";

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return id;

    }

    @Override
    public boolean updateUser_picture(int user_id, String user_picture) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        con = DbConnection.getInstance().getConnection();
        try {
            pstmt = con.prepareStatement("UPDATE user SET user_picture = ? where user_id = ? ");
            pstmt.setString(1, user_picture);
            pstmt.setInt(2, user_id);
            int count = pstmt.executeUpdate();
            if (count != 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUserio(UserPo userPo) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        con = DbConnection.getInstance().getConnection();
        try {
            pstmt = con.prepareStatement(
                    "UPDATE user SET  user_address = ?,user_sex = ?,user_signature = ?,user_name=? where user_id = ?");
            pstmt.setString(1, userPo.getUser_address());
            pstmt.setString(2, userPo.getUser_sex());
            pstmt.setString(3, userPo.getUser_signature());
            pstmt.setString(4, userPo.getUser_name());
            pstmt.setInt(5, userPo.getUser_id());
            int count = pstmt.executeUpdate();
            if (count != 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return flag;

    }

    @Override
    public List<UserPo> finAllUser() {
        // TODO Auto-generated method stub
        List<UserPo> userList = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        int count = 0;
        boolean flag = false;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            con = dbConn.getConnection();
            pstmt = con.prepareStatement("select * from user ");

            set = pstmt.executeQuery();
            userList = new ArrayList<UserPo>();
            while (set.next()) {
                UserPo userpo = new UserPo();
                userpo.setUser_id(set.getInt("user_id"));
                userpo.setUser_name(set.getString("user_name"));
                userpo.setUser_password(set.getString("user_password"));
                userpo.setUser_picture(set.getString("user_picture"));
                userpo.setUser_sex(set.getString("user_sex"));
                userpo.setUser_address(set.getString("user_address"));
                userpo.setUser_signature(set.getString("user_signature"));
                userpo.setStates(set.getInt("user_states"));
                userList.add(userpo);
            }

        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            dbConn.close(con, pstmt, set);
        }

        return userList;


    }

    public boolean updateOnlineUser_states(int user_id) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        con = DbConnection.getInstance().getConnection();
        try {

            pstmt = con.prepareStatement(
                    "UPDATE user SET  user_states = 1 where user_id = ?");
            pstmt.setInt(1, user_id);
            int count = pstmt.executeUpdate();
            if (count != 0) {
                flag = true;

            }

        } catch (Exception e) {

            e.getStackTrace();
        }

        return flag;

    }

    public boolean updateUnOnlineUser_states(int user_id) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        con = DbConnection.getInstance().getConnection();
        try {

            pstmt = con.prepareStatement(
                    "UPDATE user SET  user_states = 2 where user_id = ?");
            pstmt.setInt(1, user_id);
            int count = pstmt.executeUpdate();
            if (count != 0) {
                flag = true;

            }

        } catch (Exception e) {

            e.getStackTrace();
        }

        return flag;

    }

    public boolean checkUserState(int user_id) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        int count = 0;
        boolean flag = false;
        try {
            con = DbConnection.getInstance().getConnection();
            pstmt = con.prepareStatement("select * from user where user_id = ? and user_states = 0");
            pstmt.setInt(1, user_id);

            set = pstmt.executeQuery();
            while (set.next()) {
                count++;
            }
            if (count != 0) {
                flag = true;
            }

        } catch (SQLException e) {

        }

        return flag;
    }

    @Override
    public boolean updateUserLastLogin(int user_id, Timestamp timestamp) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        con = DbConnection.getInstance().getConnection();
        try {
            pstmt = con.prepareStatement(
                    "UPDATE user SET  user_lastLogin = ? where user_id = ?");
            pstmt.setInt(2, user_id);
            pstmt.setTimestamp(1, timestamp);
            int count = pstmt.executeUpdate();
            if (count != 0) {
                flag = true;
            }
        } catch (Exception e) {

            e.getStackTrace();
        }

        return flag;
    }

    public boolean updateFreezeUser_states(int user_id) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        con = DbConnection.getInstance().getConnection();
        try {

            pstmt = con.prepareStatement(
                    "UPDATE user SET  user_states = 0 where user_id = ?");
            pstmt.setInt(1, user_id);
            int count = pstmt.executeUpdate();
            if (count != 0) {
                flag = true;

            }

        } catch (Exception e) {

            e.getStackTrace();
        }

        return flag;

    }

    @Override
    public List<UserPo> findAllMyConcern(int user_id) {
        List<UserPo> userList = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "select u.user_id,u.user_name,u.user_password,u.user_picture,"
                    + "u.user_sex,u.user_address,u.user_signature,user_states from user u,"
                    + "fans f where f.f_fan_id=? and f.f_user_id=u.user_id";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                userList = new ArrayList<UserPo>();
                UserPo user = null;
                while (rs.next()) {
                    user = new UserPo();
                    user.setUser_id(rs.getInt("u.user_id"));
                    user.setUser_name(rs.getString("u.user_name"));
                    user.setUser_password(rs.getString("u.user_password"));
                    user.setUser_picture(rs.getString("u.user_picture"));
                    user.setUser_sex(rs.getString("u.user_sex"));
                    user.setUser_address(rs.getString("u.user_address"));
                    user.setUser_signature(rs.getString("u.user_signature"));
                    user.setStates(rs.getInt("u.user_states"));
                    userList.add(user);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return userList;
    }

    @Override
    public List<UserPo> findAllMyFans(int user_id) {
        List<UserPo> userList = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            conn = dbConn.getConnection();
            String sql = "select u.user_id,u.user_name,u.user_password,u.user_picture,"
                    + "u.user_sex,u.user_address,u.user_signature,user_states from user u,"
                    + "fans f where f.f_user_id=? and f.f_fan_id=u.user_id";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                userList = new ArrayList<UserPo>();
                UserPo user = null;
                while (rs.next()) {
                    user = new UserPo();
                    user.setUser_id(rs.getInt("u.user_id"));
                    user.setUser_name(rs.getString("u.user_name"));
                    user.setUser_password(rs.getString("u.user_password"));
                    user.setUser_picture(rs.getString("u.user_picture"));
                    user.setUser_sex(rs.getString("u.user_sex"));
                    user.setUser_address(rs.getString("u.user_address"));
                    user.setUser_signature(rs.getString("u.user_signature"));
                    user.setStates(rs.getInt("u.user_states"));
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return userList;
    }

    @Override
    public boolean findUserByUser_name(String user_name) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        int count = 0;
        boolean flag = false;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            con = DbConnection.getInstance().getConnection();
            pstmt = con.prepareStatement("select * from user where user_name = ? ");
            pstmt.setString(1, user_name);
            set = pstmt.executeQuery();
            while (set.next()) {
                count++;
            }
            if (count != 0) {
                flag = true;
            }

        } catch (SQLException e) {

        } finally {
            dbConn.close(con, pstmt, set);
        }

        return flag;
    }

    public boolean checkUserHasOnline(int user_id) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        int count = 0;
        boolean flag = false;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            con = dbConn.getConnection();
            pstmt = con.prepareStatement("select * from user where user_id = ? and user_states = 1");
            pstmt.setInt(1, user_id);

            set = pstmt.executeQuery();
            while (set.next()) {
                count++;
            }
            if (count != 0) {
                flag = true;
            }

        } catch (SQLException e) {

        } finally {
            dbConn.close(con, pstmt, set);
        }

        return flag;

    }

    public List<UserPo> findAllUser(int currPageNo, int number, String keyword) {

        List<UserPo> userList = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DbConnection dbConn = DbConnection.getInstance();

        try {
            conn = dbConn.getConnection();
            String sql = "select * from user";
            // 判断是否有查询条件，如果有，就在sql后添加where语句
            if (keyword != null && !(keyword = keyword.trim()).equals("")) {
                sql += " where user_id like ? or user_name like ?";
            }
            sql += " limit ?,?";
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
                userList = new ArrayList<UserPo>();
                UserPo userpo = null;
                while (rs.next()) {
                    userpo = new UserPo();
                    userpo.setStates(rs.getInt("user_states"));
                    userpo.setUser_address(rs.getString("user_address"));
                    userpo.setUser_id(rs.getInt("user_id"));
                    userpo.setUser_name(rs.getString("user_name"));
                    userpo.setUser_password(rs.getString("user_password"));
                    userpo.setUser_picture(rs.getString("user_picture"));
                    userpo.setUser_sex(rs.getString("user_sex"));
                    userpo.setUser_signature(rs.getString("user_signature"));
                    userList.add(userpo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        return userList;
    }


    public boolean checkUser_id(int user_id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        int count = 0;
        boolean flag = false;
        DbConnection dbConn = DbConnection.getInstance();
        try {
            con = dbConn.getConnection();
            pstmt = con.prepareStatement("select * from user where user_id = ? ");
            pstmt.setInt(1, user_id);

            set = pstmt.executeQuery();
            while (set.next()) {
                count++;
            }
            if (count != 0) {
                flag = true;
            }

        } catch (SQLException e) {

        }

        return flag;
    }


    public static void main(String[] args) {
        boolean flag = DAOFactory.buildDAOFactory().createUserDAO().updateUser_picture(2, "123");
        System.out.println(flag);
    }
}
