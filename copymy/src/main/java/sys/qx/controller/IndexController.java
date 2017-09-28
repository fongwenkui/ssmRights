package sys.qx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sys.qx.model.Menu;
import sys.qx.model.Role;
import sys.qx.model.User;
import sys.qx.queryvo.EasyUiTreeResult;
import sys.qx.queryvo.RoleInfo;
import sys.qx.queryvo.UserInfo;
import sys.qx.service.RoleMenuService;
import sys.qx.service.UserRoleService;
import sys.qx.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleServicer;
	@Autowired
	private RoleMenuService roleMenuService;

	@RequestMapping("{page}.do")
	public String page(@PathVariable String page) {
		return page;
	}

	@RequestMapping("dologin.do")
	public String dologin(String username, String password,
			HttpServletRequest request) {
		/*
		 * 1.判断用户名和密码是否为空 是:return "redirect:login.do"; 2.查询数据库得到user对象 判断是否为空
		 * 是:return "redirect:login.do"; 3.根据userid查询该用户的角色
		 * 并封装成queryVo(userInfo) 包含用户和角色 4.遍历userInfo的角色属性
		 * 1.根据roleid查询该角色拥有的所有菜单 并封装成QueryVo(roleInfo) 包含角色和该角色拥有的菜单
		 * 2.如果返回的roleInfo不等于null List<RoleInfos>.add(roleInfo);
		 * 5.将userInfo和roleInfos添加进session中 6.重定向到index.do
		 */

		// 1
		if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
			return "redirect:login.do";
		}
		// 2
		User user = userService.findUserByUsernameAndPassword(username,
				password);
		if (user == null) {
			return "redirect:login.do";
		}
		// 3
		UserInfo userInfo = userRoleServicer.getUserRoleByUser(user);
		// 4
		RoleInfo roleInfo = roleMenuService.getRoleMenuByRole(userInfo);
		HttpSession session = request.getSession();
		// 5
		session.setAttribute("userInfo", userInfo);
		session.setAttribute("roleInfo", roleInfo);

		return "index";
	}

	@RequestMapping(value = "getTreeNode.do", method = RequestMethod.GET)
	@ResponseBody
	public List<EasyUiTreeResult> getTreeNode(
			@RequestParam(defaultValue = "0", name = "id") String pid,
			HttpServletRequest request) {
		// 1.从session中取出roleInfo(包含角色和该角色拥有的菜单)
		HttpSession session = request.getSession();
		RoleInfo roleInfo = (RoleInfo) session.getAttribute("roleInfo");
		Set<Menu> menus = roleInfo.getMenus();
		List<EasyUiTreeResult> trees = new ArrayList<EasyUiTreeResult>();
		if (menus.size() > 0 && roleInfo != null) {
			// 2.遍历roleInfo.roles
			for (Menu menu : menus) {
				// 3.判断pid是否等于参数pid(如果不等于0只收录menu中code包含list的)
				if (menu.getPid().equals(pid)) {
					if (!menu.getPid().equals("0")
							&& menu.getCode().indexOf("list") == -1) {
						continue;
					}
					// 4.如果等于就添加成EasyUiTree tree
					EasyUiTreeResult tree = new EasyUiTreeResult();
					tree.setId(menu.getId());
					tree.setState(pid.equals("0") ? "closed" : "open");
					tree.setText(menu.getMenuname());
					if (tree.getState().equals("open")) {
						// 添加url
						Map<String, Object> url = new HashMap<String, Object>();
						url.put("url", menu.getCode()+".do");
						tree.setAttributes(url);
					}
					// 5.将tree添加到trees中(List<EasyUiTree>
					trees.add(tree);
				}
			}
		}
		// 6.响应json
		return trees;
	}
	
	

}
