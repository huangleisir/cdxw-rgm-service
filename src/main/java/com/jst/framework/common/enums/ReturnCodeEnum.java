/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.framework.common.enums;

public enum ReturnCodeEnum {
    /**  系统公共返回码定义  */
	
	SUCCESS("200", "成功"),
	FAIL("201", "系统繁忙"),
	SYSTEM_UNKNOWN_ERROR("0000", "系统未知错误"), 
	SYSTEM_DATA_NOTFUND("16001", "请求数据错误"), 
    SYSTEM_TIMEOUT("16002", "处理超时"), 
    SYSTEM_CONFIG_ERROR ("16003", "未成功加载配置文件"), 
    DATABASE_CONNECT_TIMEOUT("16004","连接数据库超时"),
    SQL_EXCEPTION("16005","Sql异常"),
    
    PARAM_NULL("160100","接口输入参数为空串或者null"),
    PARAM_INVALID("160101","接口输入参数不合法"),
    SING_ERROR("160102","报文校验失败"),
    APPKEY_ERROR("160103","appkey错误"),
    MSG_PARSE_ERROR("160104","报文解析错误"),
    ORDER_DUPLICATE("160105","序号重复"),
    NO_DELETE("160106","该分类下有有效菜品，无法删除"),
    USER_NUMBER_EXIST("160107","用户编号已存在"),
    USER_ID_EXIST("160108","用户名已存在"),
    USER_NUMBER_NULL("160109","用户编号为空"),
    USER_ID_NULL("160110","用户名为空"),
    USER_NAME_NULL("160110","真实姓名为空"),
    USER_SEX_NULL("160111","性别为空"),
    USER_MOBILE_NULL("160112","手机号为空"),
    USER_PASSWORD_NULL("160113","密码为空"),
    USER_STATUS_NULL("160114","状态为空"),
    RESET_PASSWORD_FAILURE("160115","重置密码失败"),
    MENU_LIST_NULL("160116","暂未查询到菜单"),
    MENU_UPDATE_FAILURE("160117","菜单更新失败"),
    LOGIN_EXCEPTION("160118","登录异常"),
    PASSWORD_ERROR("160119","密码错误"),
    USER_STATUS_DISABLE("160120","用户已被禁用"),
    USER_NOT_AUTH("160121","暂无权限,请联系管理员申请授权"),
    USER_ID_NO("160122","用户名不存在"),
    ;
    
    /* 返回码  */
    private String code;
    
    /* 返回码描述 */
    private String desc;
    
    private ReturnCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
