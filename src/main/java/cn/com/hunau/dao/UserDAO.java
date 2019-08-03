package cn.com.hunau.dao;

public interface UserDAO {
	
/**
 * 验证登录用户与密码
 */
    public boolean checklogin(String user_name, String user_password);
  
/**
 * 注册
 */  
    public boolean regist(String user_name, String user_password);
/**
 * 更换头像
 */
    public void changeuser_picture(String user_picture);
 /**
  * 编辑个人信息   
  */
    public void changeuserio(String user_address, String user_sex, String user_signature);
    
}
