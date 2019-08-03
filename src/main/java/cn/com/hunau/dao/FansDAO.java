package cn.com.hunau.dao;

public interface FansDAO {
  /**
   * 查找fans数量
   * @return
   */
  public int findfansnumber();
  /**
   * 查找我关注的人
   * @return
   */
  public int findfollow();
  
}
