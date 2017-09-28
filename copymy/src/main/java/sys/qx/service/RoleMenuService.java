package sys.qx.service;


import sys.qx.model.RoleMenu;
import sys.qx.queryvo.RoleInfo;
import sys.qx.queryvo.UserInfo;

public interface RoleMenuService {
	/**
	 * 得到RoleMenu对象(包含角色和对应的所有菜单)
	 * @return
	 */
	RoleInfo getRoleMenuByRole(UserInfo userInfo);

	/**
	 * 根据roleid删除角色对应的菜单
	 * @param id
	 */
	void delRoleMenuByRoleId(String id);

	/**
	 * 添加角色菜单权限
	 * @param roleMenu
	 * @return
	 */
	int addRoleMenu(RoleMenu roleMenu);

	/**
	 * 根据id删除
	 * @param id
	 */
	void delRoleMenuByMenuId(String id);

}
