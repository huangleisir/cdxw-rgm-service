/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.wxdc.controller;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jst.framework.common.enums.ReturnCodeEnum;
import com.jst.framework.common.util.CheckCodeUtil;
import com.jst.framework.common.util.DateUtil;
import com.jst.framework.common.util.ExportExcelUtil;
import com.jst.framework.common.util.FastDfsUtils;
import com.jst.framework.common.util.ThumbnailUtils;
import com.jst.prodution.base.bean.BaseBean;
import com.jst.prodution.merchant.model.MerchantImage;
import com.jst.prodution.merchant.serviceBean.MerchApplyBean;
import com.jst.wxdc.bean.Food;
import com.jst.wxdc.bean.FoodType;
import com.jst.wxdc.bean.Image;
import com.jst.wxdc.bean.Result;
import com.jst.wxdc.bean.SysParams;
import com.jst.wxdc.bean.UserInfo;
import com.jst.wxdc.bean.WxdcDto;
import com.jst.wxdc.service.WxdcService;



@Controller
@RequestMapping("/wxdc")
public class WxdcController extends BaseController {

	private final static Logger log = LoggerFactory.getLogger(WxdcController.class);
	  
	@Autowired
	private WxdcService wxdcService;
	  
    @Value("${uploadDir}")
    String upload; // 图片上传路径
    
	//查询所有订单
    @RequestMapping(value = "/queryOrder", method = RequestMethod.POST)
    @ResponseBody
    public Result queryOrder(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated WxdcDto dto){
        Result result = null;
            result =wxdcService.queryOrder(dto);
        return result;
    }
    
	//查询订单详情
    @RequestMapping(value = "/queryOrderDetail", method = RequestMethod.POST)
    @ResponseBody
    public Result queryOrderDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated WxdcDto dto){
       Result result = null;
           result =wxdcService.queryOrderDetail(dto);
       return result;
    }
    
    //查询订单内食物列表
    @RequestMapping(value = "/queryOrderFoodList", method = RequestMethod.POST)
    @ResponseBody
    public Result queryOrderFoodList(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated WxdcDto dto){
       Result result = null;
           result =wxdcService.queryOrderFoodList(dto);
       return result;
    }
    
    //完成订单、取消订单
    @RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
    @ResponseBody
    public Result updateOrderStatus(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated WxdcDto dto){
	    Result result = null;
	      result =wxdcService.updateOrderStatus(dto);
	    return result;
    }
        
    @RequestMapping(value = "/updateSendMoneySetting", method = RequestMethod.POST)
    @ResponseBody
    public Result updateSendMoneySetting(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated SysParams dto){
       Result result = null;
           result =wxdcService.updateSendMoneySetting(dto);
       return result;
    }
    
    //查询配送费参数
    @RequestMapping(value = "/querySendMoneySetting", method = RequestMethod.POST)
    @ResponseBody
    public Result querySendMoneySetting(HttpServletRequest request, HttpServletResponse response){
       Result result = null;
           result =wxdcService.querySendMoneySetting();
       return result;
    }
	       
    @RequestMapping(value = "downloadOrder",method = RequestMethod.GET)
    @ResponseBody
    public void download(HttpServletResponse response,HttpServletRequest request,WxdcDto record){
        try {   
            String[] rowsName = {"序号","订单编号","下单时间","订单金额(元)","姓名","手机号码","地址","支付方式","支付状态","打印状态","配送状态"};
            int[] colWidth = {80, 180, 240, 180, 180, 180,300,180,180,180,180};
            List<Object[]> dataList = wxdcService.downloadOrder(record,rowsName);
            String fileName="订单明细"+DateUtil.getDateStr(DateUtil.PATTERN_DATE_TIME_MILL_TWO);//
            String title="订单明细"+DateUtil.getDateStr(DateUtil.PATTERN_DATE_TIME_MILL_TWO);
            ExportExcelUtil ex = new ExportExcelUtil(fileName, title, rowsName, colWidth, dataList,response);  
            ex.export();
        } catch (UnsupportedEncodingException e) {
            log.error("编码转换异常");
            e.printStackTrace();
        } catch (Exception e) {
            log.error("导出Excel异常");
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = "downloadFood",method = RequestMethod.GET)
    @ResponseBody
    public void downloadFood(HttpServletResponse response,HttpServletRequest request,Food record){
        try {   
            String[] rowsName = {"序号","菜品编码","菜品名称","菜品分类","图片地址","售价(元)","原价(元)","状态","说明"};
            int[] colWidth = {80, 180, 180, 180, 300, 180,180,180,300};
            List<Object[]> dataList = wxdcService.downloadFood(record,rowsName);
            String fileName="菜品明细"+DateUtil.getDateStr(DateUtil.PATTERN_DATE_TIME_MILL_TWO);//
            String title="菜品明细"+DateUtil.getDateStr(DateUtil.PATTERN_DATE_TIME_MILL_TWO);
            ExportExcelUtil ex = new ExportExcelUtil(fileName, title, rowsName, colWidth, dataList,response);  
            ex.export();
        } catch (UnsupportedEncodingException e) {
            log.error("编码转换异常");
            e.printStackTrace();
        } catch (Exception e) {
            log.error("导出Excel异常");
            e.printStackTrace();
        }
    }
	    
	//查询菜品分类
    @RequestMapping(value = "/queryFoodType", method = RequestMethod.POST)
    @ResponseBody
    public Result queryFoodType(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated FoodType dto){
        Result result = null;
            result =wxdcService.queryFoodType(dto);
        return result;
    }
    //新增菜品分类
	@RequestMapping(value = "/addFoodType", method = RequestMethod.POST)
	@ResponseBody
	public Result addFoodType(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated FoodType dto){
      Result result = null;
      result =wxdcService.addFoodType(dto);
      return result;
	}
      
	//修改菜品分类
    @RequestMapping(value = "/updateFoodType", method = RequestMethod.POST)
    @ResponseBody
    public Result updateFoodType(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated FoodType dto){
        Result result = null;
            result =wxdcService.updateFoodType(dto);
        return result;
    }
    
    //删除菜品分类
    @RequestMapping(value = "/deleteFoodType", method = RequestMethod.POST)
    @ResponseBody
	public Result deleteFoodType(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated FoodType dto){
       Result result = null;
          result =wxdcService.deleteFoodType(dto);
       return result;
	}
  
    //输入框输入排序菜品分类
    @RequestMapping(value = "/sortFoodType", method = RequestMethod.POST)
    @ResponseBody
    public Result sortFoodType(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated FoodType dto){
	    Result result = null;
	        result =wxdcService.sortFoodType(dto);
	    return result;
    }
    
	//生成分类编码
	@RequestMapping(value = "/queryTypeId", method = RequestMethod.POST)
	@ResponseBody
	public Result queryTypeId(HttpServletRequest request, HttpServletResponse response){
	  Result result = null;
	      result =wxdcService.queryTypeId();
	  return result;
	}
	
	//查询菜品
	@RequestMapping(value = "/queryFood", method = RequestMethod.POST)
	@ResponseBody
	public Result queryFood(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated Food dto){
		  Result result = null;
		      result =wxdcService.queryFood(dto);
		  return result;
	}
	
	//删除菜品
	@RequestMapping(value = "/deleteFood", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteFood(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated Food dto){
		Result result = null;
		    result =wxdcService.deleteFood(dto);
		return result;
	}
	
	//沽清菜品、解除沽清
	@RequestMapping(value = "/updateFoodStatus", method = RequestMethod.POST)
	@ResponseBody
	public Result updateFoodStatus(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated Food dto){
		Result result = null;
		  result =wxdcService.updateFoodStatus(dto);
		return result;
	}
	
	//生成菜品编码
	@RequestMapping(value = "/queryFoodId", method = RequestMethod.POST)
	@ResponseBody
	public Result queryFoodId(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated Food dto){
		Result result = null;
		    result =wxdcService.queryFoodId(dto);
		return result;
	}
	
	//新增菜品
	@RequestMapping(value = "/addFood", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addFood(@RequestParam MultipartFile[] picPaths, Food dto) throws Exception {
	    Map<String, Object> map = new HashMap<String, Object>();
	
	    // 上传图片是否全部成功
	    boolean uploadImage = true;
	    List<Image> imageList = new ArrayList<Image>();
	    Image image = null;
	    for (MultipartFile myfile : picPaths) {
	        if (!myfile.isEmpty() && uploadImage) {
	            image = new Image();
	            image.setServerPath(upload);
	            try {
	                buildImgInfo(image, myfile);
	            } catch (Exception e) {
	                uploadImage = false;
	                log.error("生成图片失败：", e);
	                break;
	            }
	            imageList.add(image);
	        }
	    }
	    // 菜品图片
	    if (imageList.size() == 0) {
	        map.put("resCode", "CK10002");
	        map.put("msgContent", "请上传图片");
	        return map;
	    }
	    
	   dto.setPicPath(imageList.get(0).getImageMax());
	    if (uploadImage) {
	        map = wxdcService.addFood(dto);
	    }
	    return map;  
	}
	
	@RequestMapping(value = "/updateFood", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateFood(@RequestParam MultipartFile[] picPaths, Food dto) throws Exception {
	    Map<String, Object> map = new HashMap<String, Object>();
	
	    // 上传图片是否全部成功
	    boolean uploadImage = true;
	    List<Image> imageList = new ArrayList<Image>();
	    Image image = null;
	    for (MultipartFile myfile : picPaths) {
	        if (!myfile.isEmpty() && uploadImage) {
	            image = new Image();
	            image.setServerPath(upload);
	            try {
	                buildImgInfo(image, myfile);
	            } catch (Exception e) {
	                uploadImage = false;
	                log.error("生成图片失败：", e);
	                break;
	            }
	            imageList.add(image);
	        }
	    }
	    // 菜品图片
	    if (imageList.size() == 0) {
	        map =wxdcService.updateFood(dto);
	        return map;
	    }
	    dto.setPicPath(imageList.get(0).getImageMax());
	    if (uploadImage) {
	        map = wxdcService.updateFood(dto);
	    }
	    return map;  
	}
	
	//修改菜品
	/*@RequestMapping(value = "/updateFood", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateFood(@RequestParam MultipartFile[] picPaths, Food dto) throws Exception {
	    Map<String, Object> map = new HashMap<String, Object>();
	
	    // 上传图片是否全部成功
	    boolean uploadImage = true;
	    List<Image> imageList = new ArrayList<Image>();
	    Image image = null;
	    for (MultipartFile myfile : picPaths) {
	        if (!myfile.isEmpty() && uploadImage) {
	            image = new Image();
	            image.setServerPath(upload);
	            try {
	                buildImgInfo(image, myfile);
	            } catch (Exception e) {
	                uploadImage = false;
	                log.error("生成图片失败：", e);
	                break;
	            }
	            imageList.add(image);
	        }
	    }
	    // 菜品图片
	    if (imageList.size() == 0) {
	        map =wxdcService.updateFood(dto);
	        return map;
	    }
	    dto.setPicPath(imageList.get(0).getImageMax());
	    if (uploadImage) {
	        map = wxdcService.updateFood(dto);
	    }
	    return map;  
	}*/
	
	//验证码
	@RequestMapping(value = "/getCode", method = RequestMethod.GET)
	public void getCode(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
	    HttpSession session = req.getSession();
	    // 创建验证码图片
	    BufferedImage image = CheckCodeUtil.createImage(session);
	
	    // 禁止图像缓存。
	    rsp.setHeader("Pragma", "no-cache");
	    rsp.setHeader("Cache-Control", "no-cache");
	    rsp.setDateHeader("Expires", 0);
	
	    rsp.setContentType("image/jpeg");
	
	    // 图像输出
	    ServletOutputStream sos = rsp.getOutputStream();
	    ImageIO.write(image, "jpeg", sos);
	    sos.close();
	
	}
	
	//修改密码
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public Result updatePassword(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated UserInfo dto){
		Result result = null;
		result =wxdcService.updatePassword(dto);
		return result;
	}
	
	/**
	 * 生成图片信息
	 * 
	 * @param myfile
	 * @param companyFile
	 * @return
	 * @throws Exception
	 */
	private void buildImgInfo(Image image, MultipartFile myfile) throws Exception {
	    String fileName = myfile.getOriginalFilename();
	    String imageType = "jpg";
	    if (fileName.contains(".")) {
	        imageType = fileName.substring(fileName.lastIndexOf(".") + 1);
	    }
	    // 压缩图片
	    byte[] bytes = ThumbnailUtils.resize(myfile.getBytes(), 800, 700, imageType);
	    // 上传图片
	    String maxPath = FastDfsUtils.upload(bytes, fileName, myfile.getBytes().length);
	    image.setImageMax(upload + maxPath);
	
	    byte[] minbBytes = ThumbnailUtils.resize(myfile.getBytes(), 200, 200, imageType);
	    String minPath = FastDfsUtils.upload(minbBytes, fileName, myfile.getBytes().length);
	    image.setImageMin(upload + minPath);
	}
}
	     
	    
