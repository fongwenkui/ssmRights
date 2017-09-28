package sys.qx.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sys.qx.annotation.MenuCtrl;
import sys.qx.model.User;
import sys.qx.service.UserRoleService;
import sys.qx.service.UserService;
import sys.qx.util.AjaxResult;
import sys.qx.util.Message;
import sys.qx.util.StringUtils;

@Controller
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping(value="add.do")
	@ResponseBody
	@MenuCtrl("user/add")
	public AjaxResult add(User user){
		AjaxResult aj = new AjaxResult();
		//判断是否存在
		if(userService.getUserIsExist(user.getUsername())){
			aj.setErrorMsg(Message.IS_EXIST.getMessage());
		}else{
			String id = StringUtils.getUUID();
			user.setId(id);
			int result = userService.addUser(user);
			if(result <= 0){
				aj.setErrorMsg(Message.ERROR.getMessage());
			}else{
				aj.setSuccess(true);
			}
		}	
		return aj;
	}
	
	@RequestMapping("list.do")
	@MenuCtrl("user/list")
	public String list(Model model){
		return "user/list";
	}
	
	@RequestMapping(value="update.do", method = RequestMethod.POST)
	@ResponseBody
	@MenuCtrl("user/update")
	public AjaxResult update(@RequestParam("id") String id, @RequestParam(value="password", required = true) String password){
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		int result = userService.updateUser(user);		
		if(result <= 0){
			return new AjaxResult(Message.ERROR.getMessage());
		}else{
			return new AjaxResult(true);
		}
	}
	
	@RequestMapping(value="delete.do",method=RequestMethod.POST)
	@ResponseBody
	@MenuCtrl("user/delete")
	public AjaxResult delete(String id){
		userRoleService.deleteByUserId(id);
		int result = userService.delUser(id);
		if(result > 0)
			return new AjaxResult(true);
		return new AjaxResult(Message.ERROR.getMessage());
	}
}
