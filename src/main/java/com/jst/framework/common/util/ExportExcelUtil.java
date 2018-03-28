package com.jst.framework.common.util;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.jst.prodution.util.DateUtil; 

public class ExportExcelUtil{
	//导出的文件名
	private String fileName;
	//显示的导出表的标题  
    private String title;  
    //导出表的列名  
    private String[] rowsName ;  
    //导出表的列宽
    private int[] colWidth;
    
      
    private List<Object[]>  dataList = new ArrayList<Object[]>();  
      
    private HttpServletResponse  response;  
      
    public static final Integer EXPORT_EXCEL_SHEET_COUNT = 60000;    //设置每个sheet数量
    
    //构造方法，传入要导出的数据  
    public ExportExcelUtil(String fileName,String title, String[] rowsName,int[] colWidth, List<Object[]>  dataList,HttpServletResponse response){  
        this.dataList = dataList;  
        this.rowsName = rowsName;
        this.colWidth = colWidth;
        this.fileName = fileName;
        this.title = title; 
        this.response = response;
    }  
              
    /* 
     * 导出数据 
     * */  
    public void export() throws Exception{  
        try{  
            HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象  
            HSSFSheet sheet = workbook.createSheet(title+1);                  // 创建工作表  
            
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象  
            HSSFCellStyle style = this.getStyle(workbook);                  //单元格样式对象  
            setSheetColumn(sheet);//设置工作薄列宽  
            writeTitleContent(sheet,columnTopStyle);//创建标题
            int sheetNum = 1;
            int bodyRowCount = 1;//正文内容行号  
            int currentRowCount = 1;//当前的行号 
            //将查询出的数据设置到sheet对应的单元格中  
            for(int i=0;i<dataList.size();i++){  
                Object[] obj = dataList.get(i);//遍历每个对象  
                HSSFRow row = sheet.createRow(bodyRowCount);//创建所需的行数  
                for(int j=0; j<obj.length; j++){  
                    HSSFCell  cell = null;   //设置单元格的数据类型  
                   /* if(j == 0){  
                        cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);  
                        cell.setCellValue(i+1);   
                    }else{  */
                        cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);  
                        if(!"".equals(obj[j]) && obj[j] != null){  
                            cell.setCellValue(obj[j].toString());//设置单元格的值  
                        }  
                   // }  
                    cell.setCellStyle(style);//设置单元格样式  
                }  
                if(currentRowCount % this.EXPORT_EXCEL_SHEET_COUNT == 0){  
	                sheet=null;  
	                sheetNum++;//工作薄编号递增1  
	                sheet = workbook.createSheet(title+sheetNum);//创建一个新的工作薄  
	                setSheetColumn(sheet);//设置工作薄列宽  
	                bodyRowCount = 0;//正文内容行号置位为0  
	                writeTitleContent(sheet,columnTopStyle);//写入标题            
	            } 
               bodyRowCount++;
 	           currentRowCount++;
            }  
            
            if(workbook !=null){  
                try{  
                	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    String dateStr =simpleDateFormat.format(new Date());
                    fileName = new String(fileName.getBytes("GB2312"), "ISO_8859_1");
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-disposition", "attachment;filename="+fileName+".xls"); //_"+dateStr+DateUtil.getDateStr(DateUtil.PATTERN_TIME_TWO)+" 
                    OutputStream ouputStream = null;
            		try {
            			ouputStream = response.getOutputStream();
            			 workbook.write(ouputStream);  
            		} catch (IOException e) {
            			e.printStackTrace();
            		} finally {
            			try {
            				if(ouputStream != null){
            					ouputStream.flush();
            					ouputStream.close();	
            				}
            			} catch (IOException e) {
            				e.printStackTrace();
            			}   
            		}  
                }catch (IOException e){  
                    e.printStackTrace();  
                }  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
          
    }  
      
    public void setSheetColumn(HSSFSheet sheet){
		for (int i = 0; i < colWidth.length; i++) {  
            sheet.setColumnWidth(i, 32 * colWidth[i]);  
        } 
	}
    
    public void writeTitleContent(HSSFSheet sheet,HSSFCellStyle columnTopStyle){//写入标题
    	int columnNum = rowsName.length;
    	HSSFRow rowRowName = sheet.createRow(0);//在索引0的位置创建行
        // 将列头设置到sheet的单元格中  
        for(int n=0;n<columnNum;n++){  
            HSSFCell  cellRowName = rowRowName.createCell(n);//创建列头对应个数的单元格  
            cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);//设置列头单元格的数据类型  
            HSSFRichTextString text = new HSSFRichTextString(rowsName[n]);  
            cellRowName.setCellValue(text);//设置列头单元格的值  
            cellRowName.setCellStyle(columnTopStyle);//设置列头单元格样式  
        }
    }
    
    /*  
     * 列头单元格样式 
     */      
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {    
          // 设置字体  
          HSSFFont font = workbook.createFont();  
          //设置字体大小  
          font.setFontHeightInPoints((short)11);  
          //字体加粗  
          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
          //设置字体名字   
          font.setFontName("Courier New");  
          //设置样式;   
          HSSFCellStyle style = workbook.createCellStyle();  
          //设置背景颜色
          style.setFillForegroundColor(IndexedColors.TAN.getIndex());
    	  style.setFillPattern(CellStyle.SOLID_FOREGROUND);
          //设置底边框;   
          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
          //设置底边框颜色;    
          style.setBottomBorderColor(HSSFColor.BLACK.index);  
          //设置左边框;     
          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          //设置左边框颜色;   
          style.setLeftBorderColor(HSSFColor.BLACK.index);  
          //设置右边框;   
          style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
          //设置右边框颜色;   
          style.setRightBorderColor(HSSFColor.BLACK.index);  
          //设置顶边框;   
          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          //设置顶边框颜色;    
          style.setTopBorderColor(HSSFColor.BLACK.index);  
          //在样式用应用设置的字体;    
          style.setFont(font);  
          //设置自动换行;   
          style.setWrapText(false);  
          //设置水平对齐的样式为居中对齐;    
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
          //设置垂直对齐的样式为居中对齐;   
          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
          return style;      
    }  
      
    /*   
     * 列数据信息单元格样式 
     */    
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {  
          // 设置字体  
          HSSFFont font = workbook.createFont();  
          //设置字体大小  
          //font.setFontHeightInPoints((short)10);  
          //字体加粗  
          //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
          //设置字体名字   
          font.setFontName("Courier New");  
          //设置样式;   
          HSSFCellStyle style = workbook.createCellStyle();  
          //设置底边框;   
          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
          //设置底边框颜色;    
          style.setBottomBorderColor(HSSFColor.BLACK.index);  
          //设置左边框;     
          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          //设置左边框颜色;   
          style.setLeftBorderColor(HSSFColor.BLACK.index);  
          //设置右边框;   
          style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
          //设置右边框颜色;   
          style.setRightBorderColor(HSSFColor.BLACK.index);  
          //设置顶边框;   
          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          //设置顶边框颜色;    
          style.setTopBorderColor(HSSFColor.BLACK.index);  
          //在样式用应用设置的字体;    
          style.setFont(font);  
          //设置自动换行;   
          style.setWrapText(false);  
          //设置水平对齐的样式为居中对齐;    
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
          //设置垂直对齐的样式为居中对齐;   
          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
          return style;  
    } 
}