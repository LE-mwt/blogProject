package cn.com.hunau.dao;

import cn.com.hunau.po.UserPo;

import java.util.List;

public interface UserDAO {

    /**
     * 验证登录用户与密码
     */
    public boolean checkUser(String user_name, String user_password);

    /**
     * 注册
     */
    public boolean addUser(String user_name, String user_password);

    /**
     * 更换头像
     */
    public boolean updateUser_picture(String user_name, String user_picture);

    /**
     * 编辑个人信息
     */
    public boolean updateUserio(String user_name, String user_address, String user_sex, String user_signature);

    /**
     * 根据user_id查找用户所有信息
     *
     * @return
     */
    public UserPo findUserioByUser_id(int user_id);

    /**
     * 根据user_name查找用户所有信息
     */
    public UserPo findUserioByUser_name(String user_name);

    /**
     * 查询出所有用户
     */
    public List<UserPo> finAllUser();

    /**
     * 更新用户状态 用户状态  0冻结 1在线 2不在线
     */
    public boolean updateOnlineUser_states(int user_id);

    public boolean updateUnOnlineUser_states(int user_id);

    public boolean updateFreezeUser_states(int user_id);

}
