package cn.com.hunau.dao;

import cn.com.hunau.po.FansPo;

import java.util.List;

public interface FansDAO {
    /**
     * 找出用户的所有fans
     */
    public List<FansPo> findAllFansByF_user_id(int f_user_id);

    /**
     * 查找fans数量
     *
     * @return
     */
    public int findFansNumberByF_user_id(int f_user_id);

    /**
     * 查找我关注的人的数量
     *
     * @return
     */
    public int findFollowByUser_id(int f_fan_id);

    /**
     * 添加fans
     */
    public boolean addFans(int user_id, int fan_id);

    /**
     * 删除fans
     */
    public boolean reduceFans(int user_id, int fan_id);

}
