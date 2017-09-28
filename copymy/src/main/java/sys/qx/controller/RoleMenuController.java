package sys.qx.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sys.qx.annotation.MenuCtrl;
import sys.qx.model.Role;
import sys.qx.model.RoleMenu;
import sys.qx.queryvo.RoleInfo;
import sys.qx.queryvo.UserInfo;
import sys.qx.service.RoleMenuService;
import sys.qx.service.RoleService;
import sys.qx.util.AjaxResult;
import sys.qx.util.Message;
import sys.qx.util.RoleInfoJSON;
import sys.qx.util.StringUtils;

@Controller
@RequestMapping("/roleMenu/")
public class RoleMenuController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	@RequestMapping("getRoleMenus.do")
	@ResponseBody
	@MenuCtrl("roleMenu/list")
	public List<RoleInfoJSON> getRoleMenus(){
		List<Role> roles = roleService.getAllRole();
		if(!StringUtils.isNotNull(roles))
			return null;
		List<RoleInfoJSON> list = new ArrayList<RoleInfoJSON>();
		
		for (Role role : roles) {
			UserInfo userInfo=new UserInfo();
			ArrayList<Role> interimRoleList = new ArrayList<>();
			interimRoleList.add(role);
			userInfo.setRoles(interimRoleList);
			RoleInfo roleInfo = roleMenuService.getRoleMenuByRole(userInfo);
			if (roleInfo != null) {
				if (roleInfo.getRoles().size() > 0) {
					for (Role role2 : roleInfo.getRoles()) {
						RoleInfoJSON roleInfoJSON = new RoleInfoJSON();
						roleInfoJSON.setId(role2.getId());
						roleInfoJSON.setRoleName(role2.getRolename());
						if (roleInfo.getMenus().size() > 0) {
							roleInfoJSON.setMenuName(roleInfo.getMenus());
						}
						list.add(roleInfoJSON);
					}
				}
			}
		}
		
		
		
		return list;
	}

	@RequestMapping("list.do")
	@MenuCtrl("roleMenu/list")
	public String list() {
		return "roleMenu/list";
	}

	@RequestMapping("update.do")
	@ResponseBody
	@MenuCtrl("roleMenu/update")
	public AjaxResult update(@RequestParam(value="ids", required = false) String[] menuIds,@RequestParam(value = "id", required = true) String roleId){
		roleMenuService.delRoleMenuByRoleId(roleId);
		int result = insertRoleMenu(menuIds, roleId);
		if(result >= 0 ){
			return new AjaxResult(true);
		}else{
			return new AjaxResult(Message.SUCCESS.getMessage());
		}
	}
	
	
	public int insertRoleMenu(String[] menuIds, String roleId){
		int result = 0;
		if(StringUtils.isNotNull(menuIds)){
			for(String menuid : menuIds){
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setId(UUID.randomUUID().toString());
				roleMenu.setMenid(menuid);
				roleMenu.setRoleid(roleId);
				result = roleMenuService.addRoleMenu(roleMenu);
				if(result <= 0){
					return result;
				}
			}
		}
		return result;
	}
}
