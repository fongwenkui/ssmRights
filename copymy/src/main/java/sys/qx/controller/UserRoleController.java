package sys.qx.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sys.qx.annotation.MenuCtrl;
import sys.qx.model.User;
import sys.qx.model.UserRole;
import sys.qx.queryvo.UserInfo;
import sys.qx.service.RoleService;
import sys.qx.service.UserRoleService;
import sys.qx.service.UserService;
import sys.qx.util.AjaxResult;
import sys.qx.util.Message;
import sys.qx.util.StringUtils;
import sys.qx.util.UserInfoJSON;

@Controller
@RequestMapping("/userRole/")
public class UserRoleController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="getUserRoles.do")
	@ResponseBody
	@MenuCtrl("user/list") //需要有用户列表的权限
	public List<UserInfoJSON> getUserRoles(){
		List<User> users = userService.getAllUsers();
		if(!StringUtils.isNotNull(users))
			return null;
		List<UserInfo> userInfos = userRoleService.getUserRolesByUsers(users);
		if(!StringUtils.isNotNull(userInfos))
			return null;
		//包装json数据
		List<UserInfoJSON> userInfoJSONs = new ArrayList<UserInfoJSON>();
		for(UserInfo userInfo : userInfos){
			UserInfoJSON userInfoJSON = new UserInfoJSON();
			userInfoJSON.setId(userInfo.getUser().getId());
			userInfoJSON.setUsername(userInfo.getUser().getUsername());
			userInfoJSON.setPassword(userInfo.getUser().getPassword());
			userInfoJSON.setRoleName(userInfo.getRoles());
			userInfoJSONs.add(userInfoJSON);
		}
		return userInfoJSONs;
	}
	
	@RequestMapping(value="update.do", method = RequestMethod.POST)
	@ResponseBody
	@MenuCtrl("userRole/update") //需要有分配的权限
	public AjaxResult update(@RequestParam("id") String id, @RequestParam(value="ids", required = false) String[] roleIds){
		int result = 0;
		if(StringUtils.isNotNull(roleIds)){
			userRoleService.deleteByUserId(id);
			result = insertUserRoles(roleIds, id);
		}
		if(result <= 0){
			return new AjaxResult(Message.ERROR.getMessage());
		}else{
			return new AjaxResult(true);
		}
	}
	
	public int insertUserRoles(String[] roleIds, String userId){
		int result = 0;
		if(StringUtils.isNotNull(roleIds)){
			for(String roleId : roleIds){
				UserRole userRole= new UserRole();
				userRole.setUserid(userId);
				userRole.setRoleid(roleId);
				userRole.setId(UUID.randomUUID().toString());
				result = userRoleService.addUserRole(userRole);
				if(result <= 0){
					return result;
				}
			}
		}
		return result;
	}
	
	@RequestMapping("list.do")
	@MenuCtrl("userRole/list")
	public String list(){
		return "userRole/list";
	}
	
}
