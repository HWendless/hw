package com.mao.spider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ReadExcel {
    private int totalRows = 0;//总行数
    private int totalCells = 0;//构造方法

    public ReadExcel(){}
    public int getTotalRows()  { return totalRows;}
    public int getTotalCells() {  return totalCells;}

    /**
     * 读EXCEL文件，获取集合
     * @return
     */
    public List<List<String>> getExcelInfo(InputStream is,String fileName){
        //初始化集合
        List<List<String>> lList=new ArrayList<List<String>>();
        //初始化输入流
//        InputStream is = null;
        try{
            //根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if(fileName.matches("^.+\\.(?i)(xlsx)$")){
                isExcel2003 = false;
            }
            //根据新建的文件实例化输入流
//            is = new FileInputStream(filePath);
            Workbook wb = null;
            //根据excel里面的内容读取客户信息
            if(isExcel2003){//当excel是2003时
                wb = new HSSFWorkbook(is);
            }
            else{//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            //读取Excel里面客户的信息
            lList=readExcelValue(wb);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return lList;
    }
    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<List<String>> readExcelValue(Workbook wb){
        //得到第一个shell
        Sheet sheet=wb.getSheetAt(0);

        //得到Excel的行数
        this.totalRows=sheet.getLastRowNum()+1;

        //得到Excel的列数(前提是有行数)
        if(totalRows>=1 && sheet.getRow(0) != null){
            this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
        }

        List<List<String>> lList=new ArrayList<List<String>>();
        //循环Excel行数,从第二行开始。标题不入库
        for(int r=1;r<totalRows;r++){
            Row row = sheet.getRow(r);
            if (row == null) continue;
            List<String> sList=new ArrayList<String>();

            //用于过滤空白行
            boolean isBlankRow=true;
            for(int c = 0; c <this.totalCells; c++){
                if((row.getCell(c)!=null && row.getCell(c).getCellType()!=Cell.CELL_TYPE_BLANK))
                {
                    isBlankRow=false;
                }
            }

            if(isBlankRow)
            {
                continue;
            }
            //循环Excel的列
            for(int c = 0; c <this.totalCells; c++){
                Cell cell = row.getCell(c);
                if (null != cell){
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    sList.add(cell.getStringCellValue());
                }else{
                    sList.add("");
                }
            }
            //添加
            lList.add(sList);
        }
        return lList;
    }

    /**
     * 读EXCEL文件，获取集合,数据从第几行开始
     * @return
     */
    public List<List<String>> getExcelInfoFromDataLine(InputStream is,String fileName,Integer dataLine,Integer cellNum){
        //初始化集合
        List<List<String>> lList=new ArrayList<List<String>>();
        //初始化输入流
//        InputStream is = null;
        try{
            //根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if(fileName.matches("^.+\\.(?i)(xlsx)$")){
                isExcel2003 = false;
            }
            //根据新建的文件实例化输入流
//            is = new FileInputStream(filePath);
            Workbook wb = null;
            //根据excel里面的内容读取客户信息
            if(isExcel2003){//当excel是2003时
                wb = new HSSFWorkbook(is);
            }
            else{//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            //读取Excel里面客户的信息
            lList=readExcelValueFromDataLine(wb,dataLine,cellNum);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return lList;
    }
    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<List<String>> readExcelValueFromDataLine(Workbook wb,Integer dataLine,Integer cellNum){
        //得到第一个shell
        Sheet sheet=wb.getSheetAt(0);

        //得到Excel的行数
        this.totalRows=sheet.getLastRowNum()+1;
        this.totalCells=cellNum;

//        //得到Excel的列数(前提是有行数)
//        if(totalRows>=dataLine && sheet.getRow(dataLine-1) != null){
//            this.totalCells=sheet.getRow(dataLine-1).getPhysicalNumberOfCells();
//        }

        List<List<String>> lList=new ArrayList<List<String>>();
        //循环Excel行数,从第二行开始。标题不入库
        for(int r=dataLine;r<totalRows;r++){
            Row row = sheet.getRow(r);
            if (row == null) continue;
            List<String> sList=new ArrayList<String>();

            //用于过滤空白行
            boolean isBlankRow=true;
            for(int c = 0; c <this.totalCells; c++){
                if((row.getCell(c)!=null && row.getCell(c).getCellType()!=Cell.CELL_TYPE_BLANK))
                {
                    isBlankRow=false;
                }
            }

            if(isBlankRow)
            {
                continue;
            }
            //循环Excel的列
            for(int c = 0; c <this.totalCells; c++){
                Cell cell = row.getCell(c);
                if (null != cell){
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    sList.add(cell.getStringCellValue());
                }else{
                    sList.add("");
                }
            }
            //添加
            lList.add(sList);
        }
        return lList;
    }
}