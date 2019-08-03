package cn.com.hunau.vo;

import cn.com.hunau.po.UserPo;

import java.util.List;

public class UserVo extends UserPo {
    private UserPo userPo;
    //扩展的属性
    //（个人中心页面）
    //我关注的人
    private List<UserPo> myConcern;
    //我的粉丝
    private List<UserPo> myFans;

    public List<UserPo> getMyConcern() {
        return myConcern;
    }

    public void setMyConcern(List<UserPo> myConcern) {
        this.myConcern = myConcern;
    }

    public List<UserPo> getMyFans() {
        return myFans;
    }

    public void setMyFans(List<UserPo> myFans) {
        this.myFans = myFans;
    }

    public UserPo getUserPo() {
        return userPo;
    }

    public void setUserPo(UserPo userPo) {
        this.userPo = userPo;
    }
}
