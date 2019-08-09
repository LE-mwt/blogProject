package cn.com.hunau.service.impl;

import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.FansDAO;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.po.UserPo;
import cn.com.hunau.service.UserService;
import cn.com.hunau.vo.UserVo;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public UserVo findUserByid(int user_id) {
        UserDAO userDAO = DAOFactory.buildDAOFactory().createUserDAO();
        UserPo userPo = userDAO.findUserioByUser_id(user_id);
        UserVo userVo = new UserVo(userPo);
        List<UserPo> myFans = userDAO.findAllMyFans(user_id);
        userVo.setMyFans(myFans);
        List<UserPo> myConcern = userDAO.findAllMyConcern(user_id);
        userVo.setMyConcern(myConcern);
        return userVo;
    }

    @Override
    public boolean changeUserState(int user_id) {
        return false;
    }

    @Override
    public boolean changeUserPicture(int user_id, String path) {
        UserDAO userDAO = DAOFactory.buildDAOFactory().createUserDAO();
        boolean flag = userDAO.updateUser_picture(user_id, path);
        return flag;
    }

    @Override
    public boolean checkFans(int user_id, int fan_id) {
        FansDAO fansDAO = DAOFactory.buildDAOFactory().createFansDAO();
        boolean flag = fansDAO.checkFans(user_id, fan_id);
        return flag;
    }

    @Override
    public List<UserVo> finAllUser(int currPage, int number, String keyword) {
        if (currPage <= 0) {
            currPage = 1;
        }
        UserDAO userdao = DAOFactory.buildDAOFactory().createUserDAO();
        List<UserVo> userList = null;
        List<UserPo> userPoList = userdao.findAllUser(currPage, number, keyword);

        userList = new ArrayList<UserVo>();
        UserVo vo = null;

        // po转vo

        for (UserPo po : userPoList) {
            vo = new UserVo();
            vo.setStates(po.getStates());
            vo.setUser_address(po.getUser_address());
            vo.setUser_id(po.getUser_id());
            vo.setUser_name(po.getUser_name());
            vo.setUser_password(po.getUser_password());
            vo.setUser_picture(po.getUser_picture());
            vo.setUser_sex(po.getUser_sex());
            vo.setUser_signature(po.getUser_signature());
            vo.setStates(po.getStates());
            int user_id = vo.getUser_id();
            UserVo vo1 = findUserByid(po.getUser_id());
            vo.setMyFans(vo1.getMyFans());
            vo.setMyConcern(vo1.getMyConcern());
            userList.add(vo);
        }

        return userList;
    }

    public static void main(String[] args) {
//        UserVo userVo = new UserServiceImpl().findUserByid(2);
//        List<UserPo> myConcern = userVo.getMyConcern();
//        for (UserPo concern : myConcern
//        ) {
//            System.out.println(concern.getUser_name());
//        }
//        System.out.println("---------");
//        List<UserPo> myFans = userVo.getMyFans();
//        for (UserPo fan : myFans
//        ) {
//            System.out.println(fan.getUser_name());
//        }
//        boolean flag = new UserServiceImpl().checkFans(1, 2156518);
//        System.out.println(flag);
        List<UserVo> userVos = new UserServiceImpl().finAllUser(1, 5, "");
        for (UserVo user : userVos
        ) {
            System.out.println(user.getMyFans().size());
            System.out.println(user.getMyConcern().size());
        }
    }
}
