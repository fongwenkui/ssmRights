package sys.qx.util;

import java.util.Collection;

import sys.qx.model.Menu;

public class RoleInfoJSON {
	private String id;
	private String roleName;
	private String menuName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(Collection<Menu> menus) {
		String m = "";
		if(StringUtils.isNotNull(menus)){
			for(Menu menu : menus){
				m += menu.getMenuname() + ",";
			}
			m = m.substring(0, m.lastIndexOf(","));
		}
		this.menuName = m;
	}
}
