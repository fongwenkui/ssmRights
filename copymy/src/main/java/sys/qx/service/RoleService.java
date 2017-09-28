package sys.qx.service;

import java.util.List;

import sys.qx.model.Role;

public interface RoleService {

	/**
	 * 根据role名查询，存在返回tree,不存在返回false
	 * @param rolename
	 * @return
	 */
	public boolean getRoleIsExist(String rolename);

	/**
	 * 添加角色
	 */
	public int addRole(Role role);

	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Role> getAllRole();

	/**
	 * 根据id删除角色
	 * @param id
	 * @return
	 */
	public int delRole(String id);

	/**
	 * 修改rolename
	 * @param role
	 * @return
	 */
	public int update(Role role);
	
}
