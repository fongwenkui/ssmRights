package sys.qx.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sys.qx.dao.RoleMapper;
import sys.qx.dao.UserRoleMapper;
import sys.qx.model.Role;
import sys.qx.model.User;
import sys.qx.model.UserExample;
import sys.qx.model.UserRole;
import sys.qx.model.UserRoleExample;
import sys.qx.model.UserRoleExample.Criteria;
import sys.qx.queryvo.UserInfo;
import sys.qx.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	public UserInfo getUserRoleByUser(User user) {
		//封装查询对象
		UserRoleExample userRoleExample=new UserRoleExample();
		Criteria criteria = userRoleExample.createCriteria();
		criteria.andUseridEqualTo(user.getId());
		//执行查询
		List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
		UserInfo userInfo=new UserInfo(user);
		if (userRoles==null || userRoles.size()<1) {
			return userInfo;
		}
		//遍历查询结果
		List<Role> roles = userInfo.getRoles();
		for (UserRole userRole : userRoles) {
			//根据roleid查询对应的角色
			Role role = roleMapper.selectByPrimaryKey(userRole.getRoleid());
			roles.add(role);
		}
		userInfo.setRoles(roles);
		return userInfo;
		
	}

	@Override
	public void deleteByRoleId(String id) {
		UserRoleExample example=new UserRoleExample();
		example.createCriteria().andRoleidEqualTo(id);
		userRoleMapper.deleteByExample(example);
	}

	@Override
	public void deleteByUserId(String id) {
		UserRoleExample example=new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(id);
		userRoleMapper.deleteByExample(example);
		
	}

	@Override
	public List<UserInfo> getUserRolesByUsers(List<User> users) {
		List<UserInfo> UserInfos=new ArrayList<>();
		for(User user : users){
			UserInfo userInfo = getUserRoleByUser(user);
			UserInfos.add(userInfo);
		}
		return UserInfos;
	}

	@Override
	public int addUserRole(UserRole userRole) {
		return userRoleMapper.insert(userRole);
	}

	
}
