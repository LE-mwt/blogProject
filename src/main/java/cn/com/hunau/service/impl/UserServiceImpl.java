package cn.com.hunau.service.impl;

import cn.com.hunau.service.UserService;
import cn.com.hunau.vo.UserVo;

public class UserServiceImpl implements UserService {
    @Override
    public UserVo findUserByid(int user_name) {
        return null;
    }

    @Override
    public boolean registUser(UserVo userVo) {
        return false;
    }

    @Override
    public boolean changeUserState(int user_name) {
        return false;
    }
}
