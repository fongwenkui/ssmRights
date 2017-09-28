package sys.qx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sys.qx.annotation.MenuCtrl;
import sys.qx.model.Role;
import sys.qx.service.RoleMenuService;
import sys.qx.service.RoleService;
import sys.qx.service.UserRoleService;
import sys.qx.service.UserService;
import sys.qx.util.AjaxResult;
import sys.qx.util.Message;
import sys.qx.util.StringUtils;


@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleMenuService roleMenuService;

	@RequestMapping("list.do")
	@MenuCtrl("role/list")
	public String list(){
		return "role/list";
	}
	
	@RequestMapping("add.do")
	@ResponseBody
	@MenuCtrl("role/add")
	public AjaxResult add(String roleName){
		if(roleService.getRoleIsExist(roleName)){
			return new AjaxResult(Message.IS_EXIST.getMessage());
		}
		Role role=new Role();
		role.setId(StringUtils.getUUID());
		role.setRolename(roleName);
		int result = roleService.addRole(role);
		if(result > 0)
			return new AjaxResult(true);
		return new AjaxResult(Message.ERROR.getMessage());
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	@MenuCtrl("role/delete")
	public AjaxResult delete(@RequestParam String id){
		userRoleService.deleteByRoleId(id);
		roleMenuService.delRoleMenuByRoleId(id);
		int result = roleService.delRole(id);
		if(result > 0){
			return new AjaxResult(true);
		}
		return  new AjaxResult(Message.ERROR.getMessage());
	}
	
	@RequestMapping("getRoles.do")
	@ResponseBody
	@MenuCtrl("role/list")
	public List<Map<String , Object>> getRoles(){
		List<Role> rs = roleService.getAllRole();
		if(!StringUtils.isNotNull(rs)){
			return null;
		}
		List<Map<String , Object>> roles = new ArrayList<Map<String,Object>>();
		for(Role role : rs){
			Map<String , Object> m = new HashMap<String, Object>();
			m.put("id", role.getId());
			m.put("text", role.getRolename());
			roles.add(m);
		}
		return roles;
	}
	
	@RequestMapping("update.do")
	@ResponseBody
	@MenuCtrl("role/update")
	public AjaxResult update(@RequestParam("id") String id, @RequestParam("roleName") String rolename){
		Role role = new Role();
		role.setId(id);
		role.setRolename(rolename);
		int result = roleService.update(role);
		if(result > 0){
			return new AjaxResult(true);
		}
		return new AjaxResult(Message.ERROR.getMessage());
	}
	
}
