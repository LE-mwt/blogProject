package cn.com.hunau.po;

public class UserPo {
    private int user_id;//用户id
    private String user_name;//用户名
    private String user_password;//用户密码
    private String user_picture;//用户图像的路径
    private String user_sex;//用户的性别：男 女
    private String user_address;//用户地址
    private String user_signature;//用户个性签名
    private int states;//用户状态  0冻结 1在线 2不在线
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_picture() {
		return user_picture;
	}
	public void setUser_picture(String user_picture) {
		this.user_picture = user_picture;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_signature() {
		return user_signature;
	}
	public void setUser_signature(String user_signature) {
		this.user_signature = user_signature;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
}
