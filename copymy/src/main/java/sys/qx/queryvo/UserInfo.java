package sys.qx.queryvo;

import java.util.ArrayList;
import java.util.List;

import sys.qx.model.Role;
import sys.qx.model.User;

public class UserInfo {

	private User user;
	private List<Role> roles=new ArrayList<Role>();
	
	public UserInfo(User user){
		this.user=user;
	}
	public UserInfo(){
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	
	
	
}
