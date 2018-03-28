/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.wxdc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jst.framework.common.enums.ReturnCodeEnum;
import com.jst.framework.common.util.CheckCodeUtil;
import com.jst.wxdc.bean.Result;
import com.jst.wxdc.bean.User;
import com.jst.wxdc.bean.UserInfo;
import com.jst.wxdc.bean.UserQueryListBean;
import com.jst.wxdc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	  private final static Logger log = LoggerFactory.getLogger(UserController.class);
	  
	  @Autowired
	  private UserService userService;
	  
	  /**
	   * 
	  * @Title: login 
	  * @Description: TODO(这里用一句话描述这个方法的作用) 
	  * @param @param request
	  * @param @return
	  * @param @throws Exception     
	  * @return Result    
	  * @throws
	   */
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	 @ResponseBody
	 public Result login(HttpServletRequest request) throws Exception {
		    Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		    String password = request.getParameter("password");
		    String checkCode = request.getParameter("checkCode");
		    String userId = request.getParameter("userId");
		    if (StringUtils.isBlank(password)) {
		        result  =  new Result("0001", "登录密码不能为空");
		        return result;
		    }
		    if (!CheckCodeUtil.check(request.getSession(), checkCode)) {
		        result  =  new Result("0002", "验证码不匹配");
		        return result;
		    }
	        User user = new User();
	        user.setUserId(userId);
	        user.setPassword(password);
	        
	        return userService.login(user);
	        
	  }
		
	  /**
	   * 用户列表查询
	   * 
	   * @param request
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
	  @ResponseBody
	  public Result login(@RequestBody @Validated UserQueryListBean record) throws Exception {
		  log.info("已进入查询列表入口");
		  return userService.queryUserList(record);
	  } 
	  
	  /**
	   * 用户列表查询
	   * 
	   * @param request
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping(value = "/getNewId", method = RequestMethod.POST)
	  @ResponseBody
	  public Result login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  return userService.getNewId();
		  
	  }
	  
	  /**
	   * 
	  * @Title: addUserInfo 
	  * @Description: TODO(添加用户信息) 
	  * @param @param record
	  * @param @return     
	  * @return Result    
	  * @throws
	   */
	  @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
	  @ResponseBody
	  public Result addUserInfo(@RequestBody User record) throws Exception{
		  return userService.addUserInfo(record);
	  }
	  
	  /**
	   * 
	  * @Title: updateUserInfo 
	  * @Description: TODO(添加用户信息) 
	  * @param @param record
	  * @param @return     
	  * @return Result    
	  * @throws
	   */
	  @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	  @ResponseBody
	  public Result updateUserInfo(@RequestBody User record)throws Exception{
		  return userService.updateUserInfo(record);
	  }
	  
	  /**
	   * 
	  * @Title: disableUser 
	  * @Description: TODO(禁用用户) 
	  * @param @param id
	  * @param @return     
	  * @return Result    
	  * @throws
	   */
	  @RequestMapping(value = "/startOrDisableUser", method = RequestMethod.POST)
	  @ResponseBody
	  public Result startOrDisableUser(@RequestBody User record){
		  return userService.startOrDisableUser(record);
	  }
	  
	  /**
	   * 
	  * @Title: deleteUserInfo 
	  * @Description: TODO(删除用户) 
	  * @param @param record
	  * @param @return     
	  * @return Result    
	  * @throws
	   */
	  @RequestMapping(value = "/deleteUserInfo", method = RequestMethod.POST)
	  @ResponseBody
	  public Result deleteUserInfo(@RequestBody User record){
		  return userService.deleteUserInfo(record);
	  }
	  
	  /**
	   * 
	  * @Title: resetPassword 
	  * @Description: TODO(重置密码) 
	  * @param @param record
	  * @param @return     
	  * @return Result    
	  * @throws
	   */
	  @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	  @ResponseBody
	  public Result resetPassword(@RequestBody User record){
		  return userService.resetPassword(record);
	  }
	  
}
