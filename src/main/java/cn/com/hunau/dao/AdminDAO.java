package cn.com.hunau.dao;

import cn.com.hunau.po.AdminPo;

public interface AdminDAO {
    /**
     * 根据管理员名查找管理员信息
     *
     * @param admin_id 管理员用户名
     * @return
     */
    public AdminPo searchAdminByname(int admin_id);
}
