package cn.com.hunau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.com.hunau.dao.AdminDAO;
import cn.com.hunau.db.DbConnection;
import cn.com.hunau.po.AdminPo;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public AdminPo searchAdminByname(String admin_name) {
		AdminPo admin = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbConnection dbConn = DbConnection.getInstance();
		try {
			conn = dbConn.getConnection();
			String sql = "select * from admin where admin_name=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin_name);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				admin = new AdminPo();
				admin.setAdmin_id(rs.getInt("admin_id"));
				admin.setAdmin_name(rs.getString("admin_name"));
				admin.setAdmin_password(rs.getString("admin_password"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}

		return admin;

	}
}
