/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/

package com.jst.wxdc.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jst.prodution.util.AmountUtils;
import com.jst.prodution.util.DateUtil;
import com.jst.wxdc.bean.Food;
import com.jst.wxdc.bean.FoodType;
import com.jst.wxdc.bean.Result;
import com.jst.wxdc.bean.SysParams;
import com.jst.wxdc.bean.UserInfo;
import com.jst.wxdc.bean.WxdcDto;
import com.jst.wxdc.dao.WxdcDao;
/** 
 * 
 * @Package: com.jst.market.service  
 * @ClassName: MarketActivityService 
 * @Description: 营销活动创建  
 *
 * @author: hewen
 * @date: 2017年6月23日 上午9:31:22 
 * @version V1.0 
 */
@Service
public class WxdcService  {
	
	private final static Logger log = LoggerFactory.getLogger(WxdcService.class);
	
    @Autowired WxdcDao wxdcDao;
	   
	    /**
	     * 查询列表 
	     * @param dto
	     * @return
	     */
	@Transactional
		public Result queryOrder(WxdcDto dto){
	    if(!StringUtils.isBlank(dto.getStartTimes())){
            dto.setStartTimes(dto.getStartTimes()+" 00:00:00");
        }
        if(!StringUtils.isBlank(dto.getEndTimes())){
            dto.setEndTimes(dto.getEndTimes()+" 23:59:59");
        }
            Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
			Page page=new Page();
            page.setPageSize(dto.getPageSize());
            page.setPageNum(dto.getCurrentPage());
            log.info("查询入参："+JSON.toJSONString(dto));
            PageInfo<WxdcDto> pageInfo = wxdcDao.getPage("Wxdc.selectOrder",page,dto);
            log.info("查询出参："+JSON.toJSONString(pageInfo));
           result.setData(pageInfo);
			return  result;
			
		}
	
	
    @Transactional
    public Result queryOrderDetail(WxdcDto dto){
        log.info("查询订单详情入参："+JSON.toJSONString(dto));
        WxdcDto wxdcDto = wxdcDao.getOne("Wxdc.selectOrderDetail", dto);
        log.info("查询订单详情出参："+JSON.toJSONString(wxdcDto));
        Food food = new Food();
        food.setOrderId(wxdcDto.getId());
        List<Food> foodList = wxdcDao.getList("Wxdc.selectFoodDetail", food);
        wxdcDto.setFood(foodList);
        int foodTotalPrice =0;
        for (int t = 0; t < foodList.size(); t++) {
            Food fo = foodList.get(t);
            foodTotalPrice = foodTotalPrice + Integer.parseInt(fo.getPriceTotal());
        }
        wxdcDto.setFoodTotalPrice(foodTotalPrice);
        wxdcDto.setOrderTotalPrice(foodTotalPrice+Integer.parseInt(wxdcDto.getSendAmount()));
        Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
        result.setData(wxdcDto);
        return  result;
        
    }
    
    @Transactional
    public Result queryOrderFoodList(WxdcDto dto){
        Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
        Page page=new Page();
        page.setPageSize(dto.getPageSize());
        page.setPageNum(dto.getCurrentPage());
        Food food = new Food();
        food.setOrderId(dto.getId());
        log.info("查询入参："+JSON.toJSONString(food));
        PageInfo<Food> foodList = wxdcDao.getPage("Wxdc.selectFoodDetail",page, food);
        log.info("查询出参："+JSON.toJSONString(foodList));
       result.setData(foodList);
        return  result;
        
    }
    
    @Transactional
    public Result updateSendMoneySetting(SysParams dto){
        Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
        log.info("更新入参："+JSON.toJSONString(dto));
        List<SysParams> sysParams = wxdcDao.getList("Wxdc.selectSendMoney",dto);
        if(sysParams==null){
            wxdcDao.insert("Wxdc.insertSendMoney",dto);
        }else{
        int num = wxdcDao.update("Wxdc.updateSendMoney",dto);
        log.info("更新结果："+num);
        }
        return  result;
        
    }
    @Transactional
    public Result querySendMoneySetting(){
        Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
        SysParams dto = new SysParams();
        List<SysParams> sysParams = wxdcDao.getList("Wxdc.selectSendMoney",dto);
        if(sysParams!=null){
        SysParams sys = sysParams.get(0);
        log.info("查询结果："+JSON.toJSONString(sys));
        result.setData(sys);
        }
        return  result;
        
    }
    /**
     * 完成订单、取消订单
     * @param dto
     * @return
     */
    @Transactional
    public Result updateOrderStatus(WxdcDto dto){
        Result  result  = null;
        if(StringUtils.isBlank(dto.getSendStatus())){
            result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
            return result ;
        }
        if(StringUtils.isBlank(dto.getId())){
            result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
            return result ;
        }
        if("1".equals(dto.getSendStatus())){
        int num = wxdcDao.update("Wxdc.updateOrderStatus",dto);
        if(num == 1){
            result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
        }else{
            result  =  new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
            return result ;
        }
        return  result;
        }
        return  result;
    }


/**
 * 
* @Title: downReceiveGiftList  
* @Description: TODO(导出接口) 
* @param @param record
* @param @param rowsName     
* @return List<Object[]>    
* @throws
 */
public List<Object[]> downloadOrder(WxdcDto record, String[] rowsName) {
//    // TODO Auto-generated method stub
    List<WxdcDto> bean = wxdcDao.getList("Wxdc.selectOrder", record);
    log.info("查询导出数据出参"+JSON.toJSONString(bean));
    DecimalFormat df = new DecimalFormat("#.00");
    List<Object[]>  dataList = new ArrayList<Object[]>(); 
    log.info("数据封装>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>start");
        Object[] objs = null;
         for (int t = 0; t < bean.size(); t++) {
             WxdcDto wxdcDto= bean.get(t);
             objs = new Object[rowsName.length];
             objs[0] = t + 1;//编号
             objs[1] = wxdcDto.getId();//订单编号
             objs[2] = wxdcDto.getCreateTime();//下单时间
             if(Double.parseDouble(wxdcDto.getFoodAmount())<100){
                 objs[3] = "0"+df.format((Double.parseDouble(wxdcDto.getFoodAmount()))/100);//订单金额
             }else{
             objs[3] = df.format((Double.parseDouble(wxdcDto.getFoodAmount()))/100);//订单金额
             }
             objs[4] = wxdcDto.getName();//姓名
             objs[5] = wxdcDto.getMobile();//手机号码
             objs[6] = wxdcDto.getAddr();//地址
             objs[7] = "";//支付方式
             if("1".equals(wxdcDto.getPayType())){
                 objs[7] = "一卡通在线支付";
             }else if("2".equals(wxdcDto.getPayType())){
                 objs[7] = "餐到付款";
             }
             objs[8] = null;//支付状态
             if("0".equals(wxdcDto.getPayStatus())){
                 objs[8] ="未支付";
             }else if("1".equals(wxdcDto.getPayStatus())){
                 objs[8] ="已支付";
             }
             objs[9] = null;//打印状态
             if("0".equals(wxdcDto.getPrintStatus())){
                 objs[9] ="未打印";
             }else if("1".equals(wxdcDto.getPrintStatus())){
                 objs[9] ="已打印";
             }
             objs[10] = null;//配送状态
             if("0".equals(wxdcDto.getSendStatus())){
                 objs[10] ="未确认";
             }else if("1".equals(wxdcDto.getSendStatus())){
                 objs[10] ="已确认";
             }else if("2".equals(wxdcDto.getSendStatus())){
                 objs[10] ="已配送";
             }
             dataList.add(objs); 
         
    }
    log.info("数据封装>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>end");
    return dataList;
}

/**
 * 查询菜品分类
 * @param dto
 * @return
 */
@Transactional
public Result queryFoodType(FoodType dto){
    Result  result  = null;
    if("1".equals(dto.getQueryType())){
      result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
    Page page=new Page();
    page.setPageSize(dto.getPageSize());
    page.setPageNum(dto.getCurrentPage());
    log.info("查询入参："+JSON.toJSONString(dto));
    PageInfo<WxdcDto> pageInfo = wxdcDao.getPage("Wxdc.selectFoodType",page,dto);
    log.info("查询出参："+JSON.toJSONString(pageInfo));
   result.setData(pageInfo);
    return  result;
    }else if("0".equals(dto.getQueryType())){
          result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
        log.info("查询入参："+JSON.toJSONString(dto));
        List<WxdcDto> pageInfo = wxdcDao.getList("Wxdc.selectFoodType",dto);
        log.info("查询出参："+JSON.toJSONString(pageInfo));
       result.setData(pageInfo);
        return  result;
    }
    return  result;
}

/**
 * 新增菜品分类
 * @param dto
 * @return
 */
@Transactional
public Result addFoodType(FoodType dto){
    Result  result  = null;
    if(StringUtils.isBlank(dto.getTypeId())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }
    if(StringUtils.isBlank(dto.getTypeName())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }
/*    if(StringUtils.isBlank(dto.getTypeIndex())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }*/
    if(StringUtils.isBlank(dto.getStatus())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }
/*    if(StringUtils.isBlank(dto.getMark())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }*/
    if(!StringUtils.isBlank(dto.getTypeIndex())){
    List<FoodType> foodType = wxdcDao.getList("Wxdc.selectTypeIndex",dto);
    if(foodType.size()!=0){
        result = new Result(ReturnCodeEnum.ORDER_DUPLICATE.getCode(), ReturnCodeEnum.ORDER_DUPLICATE.getDesc());
        return result ;
    }
    }
    if(StringUtils.isBlank(dto.getTypeIndex())){
        dto.setTypeIndex(null);
    }
    dto.setCreateTime(DateUtil.getSysDateTime());
    dto.setLatestUpdateTime(DateUtil.getSysDateTime());
    log.info("添加菜品分类入参："+JSON.toJSONString(dto));
    int num = wxdcDao.insert("Wxdc.insertFoodType",dto);
    log.info("添加菜品分类出参："+num);
    if(num == 1){
        result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
    }else{
        result  =  new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
        return result ;
    }
   result.setData("添加成功");
    return  result;
}

/**
 * 修改菜品分类
 * @param dto
 * @return
 */
@Transactional
public Result updateFoodType(FoodType dto){
    Result  result  = null;
    if(StringUtils.isBlank(dto.getTypeId())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }
    if(StringUtils.isBlank(dto.getTypeName())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }
/*    if(StringUtils.isBlank(dto.getTypeIndex())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }*/
    if(StringUtils.isBlank(dto.getStatus())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }
/*    if(StringUtils.isBlank(dto.getMark())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }*/
    if(!StringUtils.isBlank(dto.getTypeIndex())){
    	//
        FoodType food = wxdcDao.getOne("Wxdc.selectFoodTypeById",dto);
        if(food.getTypeIndex()==null || !food.getTypeIndex().equals(dto.getTypeIndex())){
        	List<FoodType> foodType = wxdcDao.getList("Wxdc.selectTypeIndex",dto);
        	if(foodType.size()!=0){
        		result = new Result(ReturnCodeEnum.ORDER_DUPLICATE.getCode(), ReturnCodeEnum.ORDER_DUPLICATE.getDesc());
        		return result ;
        	}
        }
    }
    if(StringUtils.isBlank(dto.getTypeIndex())){
        dto.setTypeIndex(null);
    }
    dto.setLatestUpdateTime(DateUtil.getSysDateTime());
    log.info("更新菜品分类入参："+JSON.toJSONString(dto));
    int num = wxdcDao.update("Wxdc.updateFoodType",dto);
    log.info("更新菜品分类出参："+num);
    if(num == 1){
        result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
    }else{
        result  =  new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
        return result ;
    }
   result.setData("添加成功");
    return  result;
}

/**
 * 删除菜品分类
 * @param dto
 * @return
 */
@Transactional
public Result deleteFoodType(FoodType dto){
    Result  result  = null;
    if(StringUtils.isBlank(dto.getTypeId())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }
    //通过分类编号查在该分类下是否有有效菜品，无法删除
        List<Food> foodList = wxdcDao.getList("Wxdc.selectFoodByTypeId",dto);
        if(foodList.size()!=0){
            result = new Result(ReturnCodeEnum.NO_DELETE.getCode(), ReturnCodeEnum.NO_DELETE.getDesc());
            return result ;
        }

    log.info("更新菜品分类入参："+JSON.toJSONString(dto));
    int num = wxdcDao.update("Wxdc.updateFoodTypeStatus",dto);
    log.info("更新菜品分类出参："+num);
    if(num == 1){
        result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
    }else{
        result  =  new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
        return result ;
    }
   result.setData("删除成功");
    return  result;
}
/**
 * 输入框输入排序菜品分类
 * @param dto
 * @return
 */
@Transactional
public Result sortFoodType(FoodType dto){
    Result  result  = null;
    if(StringUtils.isBlank(dto.getTypeId())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }
    if(!StringUtils.isBlank(dto.getTypeIndex())){
    List<FoodType> foodType = wxdcDao.getList("Wxdc.selectTypeIndex",dto);
    if(foodType.size()!=0){
        result = new Result(ReturnCodeEnum.ORDER_DUPLICATE.getCode(), ReturnCodeEnum.ORDER_DUPLICATE.getDesc());
        return result ;
    }
    }
    if(StringUtils.isBlank(dto.getTypeIndex())){
        dto.setTypeIndex(null);
    }
    log.info("更新菜品分类序号入参："+JSON.toJSONString(dto));
    int num = wxdcDao.update("Wxdc.updateFoodType",dto);
    log.info("更新菜品分类序号出参："+num);
    if(num == 1){
        result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
    }else{
        result  =  new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
        return result ;
    }
   result.setData("添加成功");
    return  result;
}
/**
 * 生成分类编码
 * @param dto
 * @return
 */
@Transactional
public Result queryTypeId(){
    Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
    FoodType dto =new FoodType();
    //List<FoodType> pageInfo = wxdcDao.getList("Wxdc.selectFoodType",dto);
    List<FoodType> list = wxdcDao.getList("Wxdc.queryTypeId",dto);
    log.info("查询出参："+JSON.toJSONString(list));
    int typeId = 0;
    for(int i=0;i<list.size();i++){
        FoodType foodType = list.get(i);
        if(i==0){
            typeId = Integer.parseInt(foodType.getTypeId());
        }else{
            typeId = typeId>Integer.parseInt(foodType.getTypeId())?typeId:Integer.parseInt(foodType.getTypeId());
        }
    }
    String a = null;
    if(typeId<9){
         a = "0"+(typeId+1);
    }else{
         a = ""+(typeId+1); 
    }
   result.setData(a);
    return  result;
}
/**
 * 查询菜品
 * @param dto
 * @return
 */
@Transactional
public Result queryFood(Food dto){
    Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
    Page page=new Page();
    page.setPageSize(dto.getPageSize());
    page.setPageNum(dto.getCurrentPage());
    log.info("查询入参："+JSON.toJSONString(dto));
    PageInfo<Food> foodList = wxdcDao.getPage("Wxdc.selectFood",page, dto);
    log.info("查询出参："+JSON.toJSONString(foodList));
   result.setData(foodList);
    return  result;
    
}
/**
 * 
* @Title: downReceiveGiftList  
* @Description: TODO(导出接口) 
* @param @param record
* @param @param rowsName     
* @return List<Object[]>    
* @throws
 */
public List<Object[]> downloadFood(Food record, String[] rowsName) {
//    // TODO Auto-generated method stub
    List<Food> bean = wxdcDao.getList("Wxdc.selectFood", record);
    log.info("查询导出数据出参"+JSON.toJSONString(bean));
    
    List<Object[]>  dataList = new ArrayList<Object[]>(); 
    
    log.info("数据封装>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>start");
    DecimalFormat df = new DecimalFormat("#.00");
        Object[] objs = null;
         for (int t = 0; t < bean.size(); t++) {
             Food food= bean.get(t);
             objs = new Object[rowsName.length];
             objs[0] = t + 1;
             objs[1] = food.getFoodId();//菜品编码
             objs[2] = food.getFoodName();//菜品名称
             objs[3] = food.getTypeName();//菜品分类
             objs[4] = food.getPicPath();//图片地址
             if(Double.parseDouble(food.getSalePrice())<100){
                 objs[5] = "0"+df.format((Double.parseDouble(food.getSalePrice()))/100);//售价
             }else{
             objs[5] = df.format((Double.parseDouble(food.getSalePrice()))/100);//售价
             }
             if(Double.parseDouble(food.getOldPrice())<100){
                 objs[6] = "0"+df.format((Double.parseDouble(food.getOldPrice()))/100);//售价
             }else{
             objs[6] = df.format((Double.parseDouble(food.getOldPrice()))/100);//售价
             }
             objs[7] = null;//状态0删除1正常2售罄
             if("2".equals(food.getStatus())){
                 objs[7] ="售罄";
             }else if("1".equals(food.getStatus())){
                 objs[7] ="正常";
             }
             objs[8] = food.getMark();//说明
             dataList.add(objs); 
         
    }
    log.info("数据封装>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>end");
    return dataList;
}
/**
 * 删除菜品
 * @param dto
 * @return
 */
@Transactional
public Result deleteFood(Food dto){
    Result  result  = null;
    int num = wxdcDao.update("Wxdc.updateFoodStatus",dto);
    if(num == 1){
        result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
    }else{
        result  =  new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
        return result ;
    }
    return  result;
    
}
/**
 * 沽清菜品、解除沽清
 * @param dto
 * @return
 */
@Transactional
public Result updateFoodStatus(Food dto){
    Result  result  = null;
    if(StringUtils.isBlank(dto.getStatus())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }
    int num = wxdcDao.update("Wxdc.updateFoodStatus",dto);
    if(num == 1){
        result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
    }else{
        result  =  new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
        return result ;
    }
    return  result;
    
}
/**
 * 生成菜品编码
 * @param dto
 * @return
 */
@Transactional
public Result queryFoodId(Food dto){
    Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
    if(StringUtils.isBlank(dto.getTypeId())){
        result = new Result(ReturnCodeEnum.PARAM_NULL.getCode(), ReturnCodeEnum.PARAM_NULL.getDesc());
        return result ;
    }
    dto.setFoodId(dto.getTypeId());
    List<Food> pageInfo = wxdcDao.getList("Wxdc.selectAllFoodId",dto);
    log.info("查询出参："+JSON.toJSONString(pageInfo));
    int foodId = 0;
    for(int i=0;i<pageInfo.size();i++){
        Food food = pageInfo.get(i);
        if(i==0){
            foodId = Integer.parseInt(food.getFoodId().substring(2));
        }else{
            foodId = foodId>Integer.parseInt(food.getFoodId().substring(2))?foodId:Integer.parseInt(food.getFoodId().substring(2));
        }
    }
    String a = "";
    if(foodId<9){
         a = dto.getTypeId()+"00"+(foodId+1);
    }else if(foodId<99){
         a = dto.getTypeId()+"0"+(foodId+1); 
    }else{
        a = dto.getTypeId()+""+(foodId+1); 
   }
   result.setData(a);
    return  result;
}
/**
 * 增加菜品
 * @param dto
 * @return
 */
@Transactional
public Map<String, Object> addFood(Food dto){
    Map<String, Object> map = new HashMap<String, Object>();
    if(StringUtils.isBlank(dto.getFoodId())){
        map.put("resCode", "100");
        map.put("msgContent", "菜品编码为空");
        return map;
    }
    if(StringUtils.isBlank(dto.getFoodName())){
        map.put("resCode", "101");
        map.put("msgContent", "菜品名称为空");
        return map;
    }
    if(StringUtils.isBlank(dto.getSalePrice())){
        map.put("resCode", "102");
        map.put("msgContent", "菜品售价为空");
        return map;
    }
    if(StringUtils.isBlank(dto.getOldPrice())){
        map.put("resCode", "103");
        map.put("msgContent", "菜品原价为空");
        return map;
    }
    if(StringUtils.isBlank(dto.getPicPath())){
        map.put("resCode", "104");
        map.put("msgContent", "图片为空");
        return map;
    }
    
    dto.setStatus("1");
    dto.setSalePrice((int) (Double.parseDouble(dto.getSalePrice())*100)+"");
    dto.setOldPrice((int)Double.parseDouble(dto.getOldPrice())*100+"");
    dto.setLatestUpdateTime(DateUtil.getSysDateTime());
    dto.setCreateTime(DateUtil.getSysDateTime());
    log.info("新增菜品入参："+JSON.toJSONString(dto));
    int num = wxdcDao.insert("Wxdc.insertFood",dto);
    if(num == 1){
        map.put("resCode", "200");
        map.put("msgContent", "添加菜品成功");
        return map;
    }else{
        map.put("resCode", "201");
        map.put("msgContent", "添加菜品失败");
        return map;
    }
}
/**
 * 修改菜品
 * @param dto
 * @return
 */
@Transactional
public Map<String, Object> updateFood(Food dto){
    Map<String, Object> map = new HashMap<String, Object>();
    if(StringUtils.isBlank(dto.getFoodId())){
        map.put("resCode", "100");
        map.put("msgContent", "菜品编码为空");
        return map;
    }
    if(StringUtils.isBlank(dto.getTypeId())){
        map.put("resCode", "106");
        map.put("msgContent", "菜品分类编码为空");
        return map;
    }
    if(StringUtils.isBlank(dto.getFoodName())){
        map.put("resCode", "101");
        map.put("msgContent", "菜品名字为空");
        return map;
    }
    if(StringUtils.isBlank(dto.getSalePrice())){
        map.put("resCode", "102");
        map.put("msgContent", "菜品售价为空");
        return map;
    }
    if(StringUtils.isBlank(dto.getOldPrice())){
        map.put("resCode", "103");
        map.put("msgContent", "菜品原价为空");
        return map;
    }
    dto.setStatus("1");
    dto.setSalePrice((int)Double.parseDouble(dto.getSalePrice())*100+"");
    dto.setOldPrice((int)Double.parseDouble(dto.getOldPrice())*100+"");
    dto.setLatestUpdateTime(DateUtil.getSysDateTime());
    log.info("修改菜品入参："+JSON.toJSONString(dto));
    int num = wxdcDao.update("Wxdc.updateFood",dto);
    if(num == 1){
        map.put("resCode", "200");
        map.put("msgContent", "修改菜品成功");
        return map;
    }else{
        map.put("resCode", "201");
        map.put("msgContent", "修改菜品失败");
        return map;
    }
}
/**
 * 登录
 * @param dto
 * @return
 */
@Transactional
public Result login(UserInfo input){
    Result  result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
  
    log.info("查询密码入参："+JSON.toJSONString(input));
    UserInfo info = wxdcDao.getOne("Wxdc.selectPassword",input);
    log.info("查询密码出参："+JSON.toJSONString(info));
    if(info==null){
        result  =  new Result("0004", "用户名不存在");
    }else if(input.getPassword().equals(info.getPassword())){
        result  =  new Result("200", "密码正确");
        result.setData(info);
    }else{
        result  =  new Result("0004", "密码错误");
    }
    return result;
    
}
/**
 * 修改密码
 * @param dto
 * @return
 */
@Transactional
public Result updatePassword(UserInfo dto){
    Result  result  = null;
    if(StringUtils.isBlank(dto.getUserId())){
        result = new Result("0005", "userId为空");
        return result ;
    }
    if(StringUtils.isBlank(dto.getPassword())){
        result = new Result("0006", "原密码为空");
        return result ;
    }
    if(StringUtils.isBlank(dto.getNewPassword())){
        result = new Result("0007", "新密码为空");
        return result ;
    }
    log.info("查询密码入参："+JSON.toJSONString(dto));
    UserInfo info = wxdcDao.getOne("Wxdc.selectPassword",dto);
    log.info("查询密码出参："+JSON.toJSONString(info));
    if(info==null){
        result  =  new Result("0004", "原密码错误");
    }else if(dto.getPassword().equals(info.getPassword())){
        dto.setCreateTime(DateUtil.getSysDateTime());
        int num = wxdcDao.update("Wxdc.updatePassword",dto);
        if(num == 1){
            log.info("密码修改成功");
            result  =  new Result(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc());
        }else{
            result  =  new Result(ReturnCodeEnum.FAIL.getCode(), ReturnCodeEnum.FAIL.getDesc());
            return result ;
        }
    }else{
        result  =  new Result("0004", "原密码错误");
    }

    return  result;
    
}
}
		


	

		

		
		
	


		
