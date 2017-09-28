package sys.qx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sys.qx.dao.MenuMapper;
import sys.qx.model.Menu;
import sys.qx.model.MenuExample;
import sys.qx.model.MenuExample.Criteria;
import sys.qx.service.MenuService;
import sys.qx.util.StringUtils;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> getAllMenuSort() {
		return menuMapper.selectByExample(new MenuExample());
	}

	@Override
	public List<Menu> findMenuByPid(String pid) {
		MenuExample example=new MenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(pid);
		return menuMapper.selectByExample(example);
	}

	@Override
	public boolean getMenuIsExist(String menuname) {
		MenuExample example = new MenuExample();
		example.createCriteria().andMenunameEqualTo(menuname);
		List<Menu> menus = menuMapper.selectByExample(example);
		return StringUtils.isNotNull(menus);
	}
	
	@Override
	public int addMenu(Menu menu) {
		return menuMapper.insert(menu);
	}

	@Override
	public int updateMenu(Menu menu) {
		return menuMapper.updateByPrimaryKey(menu);
	}

	@Override
	public int deleteMenu(String id) {
		return menuMapper.deleteByPrimaryKey(id);
	}

}
