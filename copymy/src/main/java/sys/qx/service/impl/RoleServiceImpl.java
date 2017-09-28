package sys.qx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sys.qx.dao.RoleMapper;
import sys.qx.model.Role;
import sys.qx.model.RoleExample;
import sys.qx.service.RoleService;
import sys.qx.util.StringUtils;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	public boolean getRoleIsExist(String rolename) {
		RoleExample example = new RoleExample();
		example.createCriteria().andRolenameEqualTo(rolename);
		List<Role> roles = roleMapper.selectByExample(example);
		if(StringUtils.isNotNull(roles)){
			return true;
		}
		return false;
	}

	public int addRole(Role role) {
		return roleMapper.insert(role);
	}

	public List<Role> getAllRole() {
		return roleMapper.selectByExample(new RoleExample());
	}

	@Override
	public int delRole(String id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Role role) {
		return roleMapper.updateByPrimaryKey(role);
	}

}
