package cn.com.hunau.service;

import cn.com.hunau.vo.UserVo;

import java.util.List;

public interface UserService {
    /**
     * (个人中心页面)
     * 根据user_name找到该用户的所有信息 包括粉丝等等
     *
     * @param user_id
     * @return
     */
    UserVo findUserByid(int user_id);

    /**
     * 更改用户状态
     *
     * @param user_id
     * @return
     */
    boolean changeUserState(int user_id);

    /**
     * 更改用户头像
     *
     * @param path
     * @return
     */
    boolean changeUserPicture(int user_id, String path);

    /**
     * 判断该用户是否关注另一个（其他用户）
     *
     * @param user_id
     * @param fan_id
     * @return
     */
    boolean checkFans(int user_id, int fan_id);

    /**
     * (用户管理页面) 找出所有用户的所有信息 包括粉丝等等
     *
     * @param currPage 当前页面
     * @param number   一页多少个
     * @param keyword  关键字
     * @return
     */
    List<UserVo> finAllUser(int currPage, int number, String keyword);
}
