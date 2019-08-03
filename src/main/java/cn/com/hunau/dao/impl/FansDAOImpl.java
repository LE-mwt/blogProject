package cn.com.hunau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.hunau.dao.FansDAO;
import cn.com.hunau.db.DbConnection;
import cn.com.hunau.po.FansPo;
import cn.com.hunau.po.UserPo;

public class FansDAOImpl implements FansDAO {

	public List<FansPo> findAllFansByF_user_id(int f_user_id) {
		List<FansPo> fansList = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		int count = 0;
		boolean flag = false;

		try {
			con = DbConnection.getInstance().getConnection();
			pstmt = con.prepareStatement("select * from fans where f_user_id = ?");
			pstmt.setInt(1, f_user_id);
			set = pstmt.executeQuery();
			fansList = new ArrayList<FansPo>();
			while (set.next()) {
				FansPo fanspo = new FansPo();
				fanspo.setId(set.getInt("id"));
				fanspo.setF_fan_id(set.getInt("f_fan_id"));
				fanspo.setF_user_id(set.getInt("f_user_id"));
				fansList.add(fanspo);
			}

		} catch (SQLException e) {
			e.getStackTrace();
		}

		return fansList;

	}

	@Override

	public int findFansNumberByF_user_id(int f_user_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		int count = 0;
		boolean flag = false;
		try {
			con = DbConnection.getInstance().getConnection();
			pstmt = con.prepareStatement("select * from fans where f_user_id = ?");
			pstmt.setInt(1, f_user_id);

			set = pstmt.executeQuery();
			while (set.next()) {
				count++;
			}
			if (count != 0) {
				flag = true;
			}

		} catch (SQLException e) {

		}

		return count;
	}

	@Override
	public int findFollowByUser_id(int f_user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		int count = 0;
		boolean flag = false;
		try {
			con = DbConnection.getInstance().getConnection();
			pstmt = con.prepareStatement("select * from fans where f_fan_id = ?");
			pstmt.setInt(1, f_user_id);

			set = pstmt.executeQuery();
			while (set.next()) {
				count++;
			}
			if (count != 0) {
				flag = true;
			}

		} catch (SQLException e) {

		}

		return count;
	}

	@Override
	public boolean addFans(int f_user_id, int f_fan_id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbConnection.getInstance().getConnection();

		try {

			pstmt = con.prepareStatement("insert into fans(f_user_id,f_fan_id) values(?,?)");
			pstmt.setInt(1, f_user_id);
			pstmt.setInt(2, f_fan_id);
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
	public boolean reduceFans(int f_user_id, int f_fan_id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbConnection.getInstance().getConnection();

		try {

			pstmt = con.prepareStatement("delete from fans where f_user_id = ? and f_fan_id=?");
			pstmt.setInt(1, f_user_id);
			pstmt.setInt(2, f_fan_id);
			int count = pstmt.executeUpdate();

			if (count != 0) {
				flag = true;

			}
			System.out.println(count);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return flag;

	}

}
