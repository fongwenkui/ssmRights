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
import sys.qx.model.Menu;
import sys.qx.service.MenuService;
import sys.qx.service.RoleMenuService;
import sys.qx.util.AjaxResult;
import sys.qx.util.Message;
import sys.qx.util.StringUtils;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	@RequestMapping("list.do")
	@MenuCtrl("menu/list")
	public String list(){
		return "menu/list";
	}

	@RequestMapping("getSortMenus.do")
	@ResponseBody
	@MenuCtrl("menu/list")
	public List<Menu> getSortMenus(){
		List<Menu> menus = menuService.getAllMenuSort();
		return menus;
	}
	
	@RequestMapping("getCombobox.do")
	@MenuCtrl("menu/list")
	@ResponseBody
	public List<Map<String, String>> getCombobox(){
		List<Map<String, String>> listmap=new ArrayList<Map<String,String>>() ;
		List<Menu> menus = menuService.findMenuByPid("0");
		if (menus!=null&&menus.size()>0) {
			for (Menu menu : menus) {
				Map<String, String> map=new HashMap<String, String>();
				map.put("id", menu.getId());
				map.put("text", menu.getMenuname());
				listmap.add(map);
			}
			Map<String, String> map=new HashMap<String, String>();
			map.put("id", "0");
			map.put("text","顶级菜单");
			listmap.add(0, map);
		}
		return listmap;
	}
	
	@RequestMapping("getMenus.do")
	@ResponseBody
	@MenuCtrl("menu/list") //需要有获得列表的权限
	public List<Map<String, Object>> getMenus(){
		List<Menu> ms = getSortMenus();
		if(!StringUtils.isNotNull(ms)){
			return null;
		}
		List<Map<String , Object>> menus = new ArrayList<Map<String,Object>>();
		for(Menu menu : ms){
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", menu.getId());
			m.put("text", menu.getMenuname());
			menus.add(m);
		}
		return menus;
	}
	
	@RequestMapping("add.do")
	@ResponseBody
	@MenuCtrl("menu/add") //需要有获得添加的权限
	public AjaxResult add(Menu menu){
		if(menuService.getMenuIsExist(menu.getMenuname())){
			return new AjaxResult(Message.IS_EXIST.getMessage());
		}
		menu.setId(StringUtils.getUUID());
		int result = menuService.addMenu(menu);
		if(result > 0){
			return new AjaxResult(true);
		}
		return new AjaxResult(Message.ERROR.getMessage());
	}
	
	@RequestMapping("update.do")
	@ResponseBody
	@MenuCtrl("menu/update") //需要有获得修改的权限
	public AjaxResult update(Menu menu){
		int result = menuService.updateMenu(menu);
		if(result > 0){
			return new AjaxResult(true);
		}
		return new AjaxResult(Message.ERROR.getMessage());
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	@MenuCtrl("menu/delete")	//需要有获得删除的权限
	public AjaxResult delete(@RequestParam("id") String id){
		//先删除关联
		roleMenuService.delRoleMenuByMenuId(id);
		int result = menuService.deleteMenu(id);
		if(result > 0){
			return new AjaxResult(true);
		}
		return new AjaxResult(Message.ERROR.getMessage());
	}
	
}
