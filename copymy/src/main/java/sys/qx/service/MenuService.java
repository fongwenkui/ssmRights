package sys.qx.service;

import java.util.List;

import sys.qx.model.Menu;

public interface MenuService {

	List<Menu> getAllMenuSort();
	
	List<Menu> findMenuByPid(String pid);

	boolean getMenuIsExist(String menuname);

	int addMenu(Menu menu);

	int updateMenu(Menu menu);

	int deleteMenu(String id);
	
}
