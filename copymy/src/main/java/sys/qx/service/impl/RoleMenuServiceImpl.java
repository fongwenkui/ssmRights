package sys.qx.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sys.qx.dao.MenuMapper;
import sys.qx.dao.RoleMenuMapper;
import sys.qx.model.Menu;
import sys.qx.model.Role;
import sys.qx.model.RoleMenu;
import sys.qx.model.RoleMenuExample;
import sys.qx.model.RoleMenuExample.Criteria;
import sys.qx.queryvo.RoleInfo;
import sys.qx.queryvo.UserInfo;
import sys.qx.service.RoleMenuService;
import sys.qx.util.StringUtils;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
	
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	@Autowired
	private MenuMapper menuMapper;

	public RoleInfo getRoleMenuByRole(UserInfo userInfo) {
		RoleInfo roleInfo=new RoleInfo();
		if (userInfo.getRoles().size()<1 || userInfo==null) {
			return roleInfo;
		}
		// 遍历userInfo.roles获得所有角色
		for (Role role : userInfo.getRoles()) {
			roleInfo.getRoles().add(role);
			//根据角色查询对应的菜单id
			RoleMenuExample example=new RoleMenuExample();
			Criteria criteria = example.createCriteria();
			criteria.andRoleidEqualTo(role.getId());
			List<RoleMenu> roleMenus = roleMenuMapper.selectByExample(example);
			//遍历查询结果
			if (roleMenus!=null&&roleMenus.size()>0) {
				for (RoleMenu roleMenu : roleMenus) {
					//根据菜单id查询对应的菜单并封装到RoleInfo中(菜单可能不只一条,因为可能角色是多个,所以roleInfo的menus用set集合)
					Menu menu = menuMapper.selectByPrimaryKey(roleMenu.getMenid());
					roleInfo.getMenus().add(menu);
				}
			}
		}
		return roleInfo;
	}

	@Override
	public void delRoleMenuByRoleId(String id) {
		RoleMenuExample example=new RoleMenuExample();
		example.createCriteria().andRoleidEqualTo(id);
		roleMenuMapper.deleteByExample(example);
	}

	@Override
	public int addRoleMenu(RoleMenu roleMenu) {
		return roleMenuMapper.insert(roleMenu);
	}

	@Override
	public void delRoleMenuByMenuId(String id) {
		roleMenuMapper.deleteByPrimaryKey(id);
	}

}
