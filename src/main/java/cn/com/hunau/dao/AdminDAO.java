package cn.com.hunau.dao;

import cn.com.hunau.po.AdminPo;

public interface AdminDAO {
	/**
	 * 根据管理员名查找管理员信息
	 * 
	 * @param admin_name 管理员用户名
	 * @return
	 */
	public AdminPo searchAdminByname(String admin_name);
}
