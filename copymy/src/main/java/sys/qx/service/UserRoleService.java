package sys.qx.service;

import java.util.List;

import sys.qx.model.User;
import sys.qx.model.UserRole;
import sys.qx.queryvo.UserInfo;

public interface UserRoleService {
	/**
	 * 查询用户角色根据user对象
	 * @param user
	 * @return
	 */
	public UserInfo getUserRoleByUser(User user);
	
	public List<UserInfo> getUserRolesByUsers(List<User> users);

	/**
	 * 根据roleId删除UserRole
	 * @param id
	 */
	public void deleteByRoleId(String id);

	/**
	 * 删除用户对应的角色，根据用户id
	 * @param id
	 */
	public void deleteByUserId(String id);

	/**
	 * 添加用户角色
	 * @param userRole
	 * @return
	 */
	public int addUserRole(UserRole userRole);
}
