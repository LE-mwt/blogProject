package cn.com.hunau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.hunau.dao.FansDAO;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.db.DbConnection;
import cn.com.hunau.po.FansPo;
import cn.com.hunau.po.UserPo;

public class UserDAOImpl implements UserDAO {

	
	
	public UserPo findUserioByUser_name(String user_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		int count = 0;
		boolean flag = false;
		UserPo userpo = new UserPo();
		try {
			con = DbConnection.getInstance().getConnection();
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
		try {
			con = DbConnection.getInstance().getConnection();
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
			}

		} catch (SQLException e) {
			e.getStackTrace();
		}

		return userpo;

	}

	@Override
	public boolean checkUser(String user_name, String user_password) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		int count = 0;
		boolean flag = false;
		try {
			con = DbConnection.getInstance().getConnection();
			pstmt = con.prepareStatement("select * from user where user_name = ? and user_password = ?");
			pstmt.setString(1, user_name);
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
	public boolean addUser(String user_name, String user_password) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbConnection.getInstance().getConnection();

		try {

			pstmt = con.prepareStatement("insert into user(user_name,user_password) values(?,?)");
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_password);
			int count = pstmt.executeUpdate();

			if (count != 0) {
				flag = true;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return flag;

	}

	@Override
	public boolean updateUser_picture(String user_name, String user_picture) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbConnection.getInstance().getConnection();
		try {

			pstmt = con.prepareStatement("UPDATE user SET user_picture = ? where user_name = ? ");
			pstmt.setString(1, user_picture);
			pstmt.setString(2, user_name);
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
	public boolean updateUserio(String user_name, String user_address, String user_sex, String user_signature) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbConnection.getInstance().getConnection();
		try {

			pstmt = con.prepareStatement(
					"UPDATE user SET  user_address = ?,user_sex = ?,user_signature = ? where user_name = ?");
			pstmt.setString(1, user_address);
			pstmt.setString(2, user_sex);
			pstmt.setString(3, user_signature);
			pstmt.setString(4, user_name);
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
		
		try {
			con = DbConnection.getInstance().getConnection();
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
					"UPDATE user SET  user_states = 1 where user_name = ?");
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
					"UPDATE user SET  user_states = 2 where user_name = ?");
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
	  public boolean updateFreezeUser_states(int user_id) {
		  boolean flag = false;
			Connection con = null;
			PreparedStatement pstmt = null;
			con = DbConnection.getInstance().getConnection();
			try {

				pstmt = con.prepareStatement(
						"UPDATE user SET  user_states = 0 where user_name = ?");
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
	
}
