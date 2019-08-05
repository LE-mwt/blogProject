package cn.com.hunau.service;

import cn.com.hunau.vo.MessageVo;

import java.util.List;

public interface MessageService {

    /**
     * 找到与该用户有关的消息信息
     *
     * @param user_id
     * @return
     */
    List<MessageVo> findMessageByUser(int user_id);
}
