package cn.com.hunau.service;

import cn.com.hunau.vo.UserVo;

public interface UserService {
    /**
     * (个人中心页面)
     * 根据user_name找到该用户的所有信息 包括粉丝等等
     *
     * @param user_name
     * @return
     */
    UserVo findUserByid(int user_name);

    /**
     * 用户注册
     *
     * @param userVo
     * @return
     */
    boolean registUser(UserVo userVo);


    /**
     * 更改用户状态
     *
     * @param user_name
     * @return
     */
    boolean changeUserState(int user_name);
}
