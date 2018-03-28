package com.jst.wxdc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jst.wxdc.bean.MenuCtrl;
import com.jst.wxdc.bean.MenuTree;
import com.jst.wxdc.bean.Result;
import com.jst.wxdc.bean.User;
import com.jst.wxdc.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	private final static Logger log = LoggerFactory.getLogger(MenuController.class);
	  
	  @Autowired
	  private MenuService menuService;
	  
	  /**
	   * 
	  * @Title: queryUserMenuTreeList 
	  * @Description: TODO(用户权限树查询) 
	  * @param @param record
	  * @param @return
	  * @param @throws Exception     
	  * @return Result    
	  * @throws
	   */
	  @RequestMapping(value = "/queryUserMenuTreeList", method = RequestMethod.POST)
	  @ResponseBody
	  public Result queryUserMenuTreeList(@RequestBody User record) throws Exception {
		  return menuService.queryUserMenuTreeList(record.getId());
	  } 
	  
	  /**
	   * 
	  * @Title: updateUserAuth 
	  * @Description: TODO(更新用户权限) 
	  * @param @param record
	  * @param @return
	  * @param @throws Exception     
	  * @return Result    
	  * @throws
	   */
	  @RequestMapping(value = "/updateUserAuth", method = RequestMethod.POST)
	  @ResponseBody
	  public Result updateUserAuth(@RequestBody MenuCtrl record) throws Exception {
		  return menuService.updateUserAuth(record);
	  } 
	  
	  /**
	   * 
	  * @Title: queryUserMenuList 
	  * @Description: TODO(查询用户菜单列表) 
	  * @param @param record
	  * @param @return     
	  * @return Result    
	  * @throws
	   */
	  @RequestMapping(value = "/queryUserMenuList", method = RequestMethod.POST)
	  @ResponseBody
	  public Result queryUserMenuList(@RequestBody MenuCtrl record){
		  return menuService.queryUserMenuList(record);
	  }
}
