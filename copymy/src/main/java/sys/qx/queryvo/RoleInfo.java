package sys.qx.queryvo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sys.qx.model.Menu;
import sys.qx.model.Role;

public class RoleInfo {

	private List<Role> roles=new ArrayList<Role>();
	private Set<Menu> menus=new HashSet<Menu>();
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
	

	
	
	
}
