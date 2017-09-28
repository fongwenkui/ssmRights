package sys.qx.util;

import java.util.List;

import sys.qx.model.Role;

public class UserInfoJSON {
	private String id;
	private String username;
	private String password;
	private String roleName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(List<Role> roles) {
		String r = "";
		if(StringUtils.isNotNull(roles)){
			for(Role role : roles){
				r += role.getRolename() + ",";
			}
			r = r.substring(0, r.lastIndexOf(","));
		}
		this.roleName = r;
	}
	@Override
	public String toString() {
		return "UserInfoJSON [id=" + id + ", username=" + username
				+ ", password=" + password + ", roleName=" + roleName + "]";
	}
	 
}
