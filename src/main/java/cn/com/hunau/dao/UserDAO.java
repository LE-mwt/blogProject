package cn.com.hunau.dao;

import cn.com.hunau.po.UserPo;

import java.sql.Timestamp;
import java.util.List;

public interface UserDAO {

    /**
     * 验证登录用户与密码
     */
    public boolean checkUser(int user_nid, String user_password);


    /**
     * 注册
     */
    public String addUser(String user_name, String user_password);

    /**
     * 更换头像
     */
    public boolean updateUser_picture(int user_id, String user_picture);

    /**
     * 编辑个人信息
     */
    public boolean updateUserio(UserPo userPo);

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
     * 根据姓名查找用户是否已经存在
     */
    public boolean findUserByUser_name(String user_name);

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

    /**
     * 根据用户名查找出所有关注的人的信息
     *
     * @param user_id
     * @return
     */
    public List<UserPo> findAllMyConcern(int user_id);

    /**
     * 根据用户名查找出所有粉丝的信息
     *
     * @param user_id
     * @return
     */
    public List<UserPo> findAllMyFans(int user_id);

    public boolean checkUserState(int user_id);

    boolean updateUserLastLogin(int user_id, Timestamp timestamp);

    //检查用户是否在线
    public boolean checkUserHasOnline(int user_id);

    //检查用户是否存在
    public boolean checkUser_id(int user_id);

    /**
     * 查找出所有0,1,2状态的用户
     *
     * @param currPageNo 当前页码
     * @param number     每页显示条数
     * @param keyword    搜索用户的关键字
     * @return
     */
    public List<UserPo> findAllUser(int currPageNo, int number, String keyword);

}
