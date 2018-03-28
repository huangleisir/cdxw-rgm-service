package com.jst.wxdc.config;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jst.wxdc.dao.MenuDao;
import com.jst.wxdc.dao.UserDao;
import com.jst.wxdc.dao.WxdcDao;
import com.jst.wxdc.dao.base.BaseDao;



/**
 * 
 * 
 * @Package: 
 * @ClassName: DruidDataSourceConfig 
 * @Description: Druid的DataResource配置类
 *
 * @author: lixin 
 * @date: 2016年12月14日 下午9:15:45 
 * @version V1.0
 */

@Configuration  
public class DaoConfig  {

	@Resource
	SqlSessionFactory sqlSessionFactory ;
	@Resource
	SqlSessionFactory wxdcSqlSessionFactory ;
	@Resource
	SqlSessionFactory userSqlSessionFactory ;
	@Resource
	SqlSessionFactory menuSqlSessionFactory ;
    
    @Bean(name = "baseDao")
    public BaseDao baseDao()  throws Exception {
    	BaseDao baseDao = new BaseDao() ;
    	baseDao.setSqlSessionFactory(sqlSessionFactory);
    	return baseDao ;
    }
    
    @Bean(name = "wxdcDao")
    public WxdcDao wxdcDao()  throws Exception {
        WxdcDao wxdcDao = new WxdcDao() ;
        wxdcDao.setSqlSessionFactory(wxdcSqlSessionFactory);
    	return wxdcDao ;
    }
    
    @Bean(name = "userDao")
    public UserDao userDao()  throws Exception {
        UserDao userDao = new UserDao() ;
        userDao.setSqlSessionFactory(userSqlSessionFactory);
    	return userDao ;
    }
    
    @Bean(name = "menuDao")
    public MenuDao menuDao()  throws Exception {
        MenuDao menuDao = new MenuDao() ;
        menuDao.setSqlSessionFactory(menuSqlSessionFactory);
    	return menuDao ;
    }


}
