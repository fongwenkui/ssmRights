package sys.qx.interceptor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import sys.qx.annotation.MenuCtrl;
import sys.qx.model.Menu;
import sys.qx.queryvo.RoleInfo;


public class MenuInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
		
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object arg2) throws Exception {
		if(arg2 instanceof HandlerMethod){
			HandlerMethod hm = (HandlerMethod) arg2;
			MenuCtrl menuCtrl = hm.getMethodAnnotation(MenuCtrl.class);
			if(null == menuCtrl){
				return true;
			}
			HttpSession session = req.getSession();
			RoleInfo roleInfo= (RoleInfo) session.getAttribute("roleInfo");
			boolean flag = false;			
			if(roleInfo == null || roleInfo.getMenus().size() < 1){
				return false;
			}else{
				for(Menu menu : roleInfo.getMenus()){
							if(menu.getCode() != null && menu.getCode().length()>0){
								if(menu.getCode().equals(menuCtrl.value())){
									flag = true;
									break;
								}
							}
				}
				if(!flag){
					resp.sendRedirect(req.getContextPath() + "/error.do");
				}else{
					return true;
				}
			}
		}
		return false;
	}


	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
