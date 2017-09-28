package sys.qx.service;

import java.util.List;

import sys.qx.model.User;

public interface UserService {

	/**
	 * 登陆
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUserByUsernameAndPassword(String username,String password);

	/**
	 * 判断用户是否存在
	 * @param username
	 * @return
	 */
	public boolean getUserIsExist(String username);

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int addUser(User user);

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public int updateUser(User user);

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int delUser(String id);

	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> getAllUsers();
	
}
