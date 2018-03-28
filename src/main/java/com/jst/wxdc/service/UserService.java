package com.jst.wxdc.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.jst.framework.common.enums.ReturnCodeEnum;
import com.jst.wxdc.bean.Menu;
import com.jst.wxdc.bean.MenuCtrl;
import com.jst.wxdc.bean.Result;
import com.jst.wxdc.bean.User;
import com.jst.wxdc.bean.UserQueryListBean;
import com.jst.wxdc.common.util.DateUtil;
import com.jst.wxdc.dao.MenuDao;
import com.jst.wxdc.dao.UserDao;

/**
 * 
* @ClassName: UserService 
* @Description: TODO(用户实现类) 
* @author 曾传保 
* @date 2018年3月20日 下午2:25:10 
*
 */
@Service
public class UserService {
	private final static Logger log = LoggerFactory.getLogger(UserService.class);
	
    @Autowired UserDao userDao;
	@Autowired MenuDao menuDao;
    
    
    /**
     * 
    * @Title: queryUserList 
    * @Description: TODO(查询用户列表) 
    * @param @param dto
    * @param @return     
    * @return Result    
    * @throws
     */
	@Transactional
	public Result queryUserList(UserQueryListBean userQueryListBean){
        Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		Page page=new Page();
        page.setPageSize(userQueryListBean.getPageSize());
        page.setPageNum(userQueryListBean.getCurrentPage());
        log.info("查询入参："+JSON.toJSONString(userQueryListBean));
        PageInfo<User> userList = userDao.getPage("User.selectUserList",page,userQueryListBean);
        log.info("查询出参："+JSON.toJSONString(userList));
        result.setData(userList);
		return  result;
	}
	
	/**
	 * 
	* @Title: getNewId 
	* @Description: TODO(获取最新用户编号) 
	* @param @return     
	* @return Result    
	* @throws
	 */
	public Result getNewId() {
		Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		try {
			 String id = userDao.getOne("User.getMaxId", "");
			 Integer index = Integer.parseInt(id);
			 String newId = "";
			 if(index < 9){
				 newId = "0"+(index+1);
			 }else{
				 newId = ""+(index+1);
			 }
			 result.setData(newId);
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: addUserInfo 
	* @Description: TODO(添加用户信息) 
	* @param @param user
	* @param @return     
	* @return Result    
	* @throws
	 */
	@Transactional
	public Result addUserInfo(User user){
		Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		if(StringUtils.isBlank(user.getId())){
			result = new Result(ReturnCodeEnum.USER_NUMBER_NULL.getCode(), ReturnCodeEnum.USER_NUMBER_NULL.getDesc());
        	return result;
		}
		if(StringUtils.isBlank(user.getUserId())){
			result = new Result(ReturnCodeEnum.USER_ID_NULL.getCode(), ReturnCodeEnum.USER_ID_NULL.getDesc());
        	return result;
		}
		if(StringUtils.isBlank(user.getUserName())){
			result = new Result(ReturnCodeEnum.USER_NAME_NULL.getCode(), ReturnCodeEnum.USER_NAME_NULL.getDesc());
        	return result;
		}
		if(StringUtils.isBlank(user.getSex())){
			result = new Result(ReturnCodeEnum.USER_SEX_NULL.getCode(), ReturnCodeEnum.USER_SEX_NULL.getDesc());
        	return result;
		}
		if(StringUtils.isBlank(user.getMobile())){
			result = new Result(ReturnCodeEnum.USER_MOBILE_NULL.getCode(), ReturnCodeEnum.USER_MOBILE_NULL.getDesc());
        	return result;
		}
		if(StringUtils.isBlank(user.getPassword())){
			result = new Result(ReturnCodeEnum.USER_PASSWORD_NULL.getCode(), ReturnCodeEnum.USER_PASSWORD_NULL.getDesc());
        	return result;
		}
        //校验用户编号是否存在
        User checkId = new User();
        checkId.setId(user.getId());
        checkId = userDao.getOne("User.checkUserInfo",checkId);
        if(checkId != null){
        	result = new Result(ReturnCodeEnum.USER_NUMBER_EXIST.getCode(), ReturnCodeEnum.USER_NUMBER_EXIST.getDesc());
        	return result;
        }
        //校验用户名是否存在
        User checkUserId = new User();
        checkUserId.setUserId(user.getUserId());
        checkUserId = userDao.getOne("User.checkUserInfo",checkUserId);
        if(checkUserId != null){
        	result = new Result(ReturnCodeEnum.USER_ID_EXIST.getCode(), ReturnCodeEnum.USER_ID_EXIST.getDesc());
        	return result;
        }
        //写入数据库
        try {
        	user.setCreateTime(DateUtil.getSysDateTime());
        	Integer index = userDao.insert("User.addUserInfo",user);
        	if(index <= 0){
        		result = new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
        	}
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getCode(), ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getDesc());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: updateUserInfo 
	* @Description: TODO(修改用户信息) 
	* @param @param user
	* @param @return     
	* @return Result    
	* @throws
	 */
	@Transactional
	public Result updateUserInfo(User user){
		Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		if(StringUtils.isBlank(user.getId())){
			result = new Result(ReturnCodeEnum.USER_NUMBER_NULL.getCode(), ReturnCodeEnum.USER_NUMBER_NULL.getDesc());
        	return result;
		}
		if(StringUtils.isBlank(user.getUserName())){
			result = new Result(ReturnCodeEnum.USER_NAME_NULL.getCode(), ReturnCodeEnum.USER_NAME_NULL.getDesc());
        	return result;
		}
		if(StringUtils.isBlank(user.getSex())){
			result = new Result(ReturnCodeEnum.USER_SEX_NULL.getCode(), ReturnCodeEnum.USER_SEX_NULL.getDesc());
        	return result;
		}
		if(StringUtils.isBlank(user.getMobile())){
			result = new Result(ReturnCodeEnum.USER_MOBILE_NULL.getCode(), ReturnCodeEnum.USER_MOBILE_NULL.getDesc());
        	return result;
		}
		//写入数据库
		try {
			Integer index = userDao.insert("User.updateUserInfo",user);
			if(index <= 0){
				result = new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
			}
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getCode(), ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getDesc());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: startOrDisableUser 
	* @Description: TODO(启用/禁用用户) 
	* @param @param record
	* @param @return     
	* @return Result    
	* @throws
	 */
	@Transactional
	public Result startOrDisableUser(User user) {
		Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		if(StringUtils.isBlank(user.getId())){
			result = new Result(ReturnCodeEnum.USER_NUMBER_NULL.getCode(), ReturnCodeEnum.USER_NUMBER_NULL.getDesc());
        	return result;
		}
		if(StringUtils.isBlank(user.getStatus())){
			result = new Result(ReturnCodeEnum.USER_STATUS_NULL.getCode(), ReturnCodeEnum.USER_STATUS_NULL.getDesc());
        	return result;
		}
		try {
			User userBean = new User();
			userBean.setId(user.getId());
			userBean.setStatus("0".equals(user.getStatus())?"1":"0");
			Integer index = userDao.insert("User.updateUserInfo",userBean);
			if(index <= 0){
				result = new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
			}
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getCode(), ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getDesc());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: deleteUserInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param record
	* @param @return     
	* @return Result    
	* @throws
	 */
	@Transactional
	public Result deleteUserInfo(User user) {
		Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		if(StringUtils.isBlank(user.getId())){
			result = new Result(ReturnCodeEnum.USER_NUMBER_NULL.getCode(), ReturnCodeEnum.USER_NUMBER_NULL.getDesc());
        	return result;
		}
		try {
			Integer index = userDao.insert("User.deleteUserInfo",user);
			if(index <= 0){
				result = new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
			}
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getCode(), ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getDesc());
			e.printStackTrace();
		}
		return result;
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
	public Result resetPassword(User user) {
		Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		if(StringUtils.isBlank(user.getId())){
			result = new Result(ReturnCodeEnum.USER_NUMBER_NULL.getCode(), ReturnCodeEnum.USER_NUMBER_NULL.getDesc());
        	return result;
		}
		if(StringUtils.isBlank(user.getPassword())){
			result = new Result(ReturnCodeEnum.USER_PASSWORD_NULL.getCode(), ReturnCodeEnum.USER_PASSWORD_NULL.getDesc());
        	return result;
		}
		try {
			Integer index = userDao.insert("User.resetPassword",user);
			if(index <= 0){
				result = new Result(ReturnCodeEnum.RESET_PASSWORD_FAILURE.getCode(), ReturnCodeEnum.RESET_PASSWORD_FAILURE.getDesc());
			}
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getCode(), ReturnCodeEnum.SYSTEM_UNKNOWN_ERROR.getDesc());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: login 
	* @Description: TODO(用户登录) 
	* @param @param user
	* @param @return     
	* @return Result    
	* @throws
	 */
	public Result login(User user) {
		Result result = new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
		User userInfo = null;
		try {
			userInfo = userDao.getOne("User.checkUserInfo", user);
			if(userInfo == null){
				result = new Result(ReturnCodeEnum.USER_ID_NO.getCode(), ReturnCodeEnum.USER_ID_NO.getDesc());
				return result;
			}else{
				if(!(userInfo.getPassword().equals(user.getPassword()))){
					result = new Result(ReturnCodeEnum.PASSWORD_ERROR.getCode(), ReturnCodeEnum.PASSWORD_ERROR.getDesc());
					return result;
				}
				//0启用   1 禁用
				if("1".equals(userInfo.getStatus())){
					result = new Result(ReturnCodeEnum.USER_STATUS_DISABLE.getCode(), ReturnCodeEnum.USER_STATUS_DISABLE.getDesc());
					return result;
				}
				//查询该用户是否具备权限
				MenuCtrl menuCtrl = new MenuCtrl();
				menuCtrl.setId(userInfo.getId());
				List<Menu> menuList = menuDao.getList("Menu.selectUserMenuList",menuCtrl);
				if(menuList == null || menuList.size() <= 0){
					result = new Result(ReturnCodeEnum.USER_NOT_AUTH.getCode(), ReturnCodeEnum.USER_NOT_AUTH.getDesc());
					return result;
				}
			}
		} catch (Exception e) {
			result = new Result(ReturnCodeEnum.LOGIN_EXCEPTION.getCode(), ReturnCodeEnum.LOGIN_EXCEPTION.getDesc());
			e.printStackTrace();
		}
		result.setData(userInfo);
		return result;
	}
}
