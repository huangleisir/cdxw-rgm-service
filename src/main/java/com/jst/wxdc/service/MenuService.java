package com.jst.wxdc.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jst.framework.common.enums.ReturnCodeEnum;
import com.jst.framework.common.exception.JstException;
import com.jst.prodution.util.DateUtil;
import com.jst.wxdc.bean.Menu;
import com.jst.wxdc.bean.MenuCtrl;
import com.jst.wxdc.bean.MenuTree;
import com.jst.wxdc.bean.Result;
import com.jst.wxdc.dao.MenuDao;

/**
 * 
* @ClassName: MenuService 
* @Description: TODO(权限操作类) 
* @author A18ccms a18ccms_gmail_com 
* @date 2018年3月22日 下午5:06:00 
*
 */
@Service
public class MenuService {
	private final static Logger log = LoggerFactory.getLogger(MenuService.class);
	@Autowired MenuDao menuDao;
	
	/**
	 * 
	* @Title: queryUserMenuList 
	* @Description: TODO(查询用户菜单) 
	* @param @param record
	* @param @return     
	* @return Result    
	* @throws
	 */
	public Result queryUserMenuTreeList(String id) {
		Result result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		if(StringUtils.isBlank(id)){
			result = new Result(ReturnCodeEnum.USER_NUMBER_NULL.getCode(), ReturnCodeEnum.USER_NUMBER_NULL.getDesc());
        	return result;
		}
		List<MenuTree> menuTreeList = null;
		List<MenuCtrl> menuCtrlList = null;
		//查询所有菜单
		try {
			Menu menu = new Menu();
			menuTreeList = menuDao.getList("Menu.selectMenuTreeList",menu);
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getCode(), ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getDesc());
			e.printStackTrace();
		}
		if(menuTreeList == null){
			result = new Result(ReturnCodeEnum.MENU_LIST_NULL.getCode(), ReturnCodeEnum.MENU_LIST_NULL.getDesc());
			return result;
		}
		
		//查询该用户拥有关联的菜单Id
		try {
			MenuCtrl menuCtrl = new MenuCtrl();
			menuCtrl.setId(id);
			menuCtrlList = menuDao.getList("Menu.selectMenuCtrlList",menuCtrl);
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getCode(), ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getDesc());
			e.printStackTrace();
		}
		
		try {
			for(MenuTree tree:menuTreeList){
				for(MenuCtrl ctrl:menuCtrlList){
					if(tree.getId().equals(ctrl.getMenuId())){
						tree.setChecked(true);
					}
				}
			}
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getCode(), ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getDesc());
			e.printStackTrace();
		}
		result.setData(menuTreeList);
		return result;
	}

	/**
	 * 
	* @Title: updateUserAuth 
	* @Description: TODO(更新用户权限) 
	* @param @return     
	* @return Result    
	* @throws
	 */
	@Transactional
	public Result updateUserAuth(MenuCtrl menuCtrl) {
		Result result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		if(StringUtils.isBlank(menuCtrl.getId())){
			result = new Result(ReturnCodeEnum.USER_NUMBER_NULL.getCode(), ReturnCodeEnum.USER_NUMBER_NULL.getDesc());
        	return result;
		}
		try {
			//删除该用户所有权限
			Integer index = menuDao.delete("Menu.deleteUserMenuCtrl",menuCtrl);
	
			//插入该用户关联新权限
			String[] menuIdList = null;
			if(StringUtils.isNotEmpty(menuCtrl.getCheckMenuId())){
				menuIdList = menuCtrl.getCheckMenuId().split(",");
			}
			MenuCtrl menuCtrlBean = new MenuCtrl();
			if(menuIdList != null){
				for(String menuId:menuIdList){
					menuCtrlBean.setId(menuCtrl.getId());
					menuCtrlBean.setMenuId(menuId);
					menuCtrlBean.setCreater("admin");
					menuCtrlBean.setCreateTime(DateUtil.getSysDateTime());
					menuCtrlBean.setUpdater("admin");
					menuCtrlBean.setUpdateTime(DateUtil.getSysDateTime());
					Integer row = menuDao.insert("Menu.addUserMenuCtrl",menuCtrlBean);
					if(row <= 0){
						new JstException(ReturnCodeEnum.MENU_UPDATE_FAILURE.getCode(), ReturnCodeEnum.MENU_UPDATE_FAILURE.getDesc());
					}
				}
			}
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.MENU_UPDATE_FAILURE.getCode(), ReturnCodeEnum.MENU_UPDATE_FAILURE.getDesc());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: queryUserMenuList 
	* @Description: TODO(查询用户菜单) 
	* @param @param menuCtrl
	* @param @return     
	* @return Result    
	* @throws
	 */
	public Result queryUserMenuList(MenuCtrl menuCtrl){
		Result result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		if(StringUtils.isBlank(menuCtrl.getId())){
			result = new Result(ReturnCodeEnum.USER_NUMBER_NULL.getCode(), ReturnCodeEnum.USER_NUMBER_NULL.getDesc());
        	return result;
		}
		List<Menu> menuList = null;
		try {
			menuList = menuDao.getList("Menu.selectUserMenuList",menuCtrl);
			if(menuList == null){
				result = new Result(ReturnCodeEnum.MENU_LIST_NULL.getCode(), ReturnCodeEnum.MENU_LIST_NULL.getDesc());
				return result;
			}
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getCode(), ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getDesc());
			e.printStackTrace();
		}
		result.setData(menuList);
		return result;
	}
	
}
