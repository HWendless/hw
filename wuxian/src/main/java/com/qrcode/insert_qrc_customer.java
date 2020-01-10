package com.qrcode;

import com.hw.cy.entity.CREATE_UUID;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class insert_qrc_customer {

    public static void main(String[] args) {
        Set<String> set=new HashSet();
        List<vo> l=new ArrayList<vo>();


        //excel文件路径
        String excelPath = "D:\\dm_cyq.xlsx";

        try {
            //String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(excel);
                }else {
                    System.out.println("文件类型错误!");
                    return;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: "+firstRowIndex);
                System.out.println("lastRowIndex: "+lastRowIndex);

                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        Cell celllllll = row.getCell(13);
                        if(set.add(celllllll.toString())){
                            vo v=new vo();
                            v.setID(CREATE_UUID.getId());
                            v.setCUSTOMER_CODE("112");
                            v.setCUSTOMER_NAME("2123");
                            v.setCUSTOMER_ORDER(123);
                            v.setIS_ACTIVE("1");
                            v.setDELIVERYLINE_CODE("111");
                            v.setLAT(new BigDecimal(row.getCell(7).toString()));
                            v.setLON(new BigDecimal(row.getCell(8).toString()));
                            v.setCUSTOMER_NAME(row.getCell(13).toString());
//                            for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
//
//                                Cell cell = row.getCell(cIndex);
//                                if (cell != null) {
//                                    if(cIndex==7) {
//                                        v.setLAT(cell.toString());
//
//                                    }if(cIndex==8) {
//                                        v.setLON(cell.toString());
//
//                                    }if(cIndex==13) {
//                                        v.setCUSTOMER_NAME(cell.toString());
//
//                                    }
//                                    System.out.println(cell.toString());
//                                }
//
//                            }
                            l.add(v);

                        }

                    }
                    System.out.println("--------------");
                }
            } else {
                System.out.println("找不到指定的文件");
            }
            for(vo vvv:l){
                System.out.println(vvv.getID());
            }
            System.out.println(l.size());



            //数据库操作
            //定义读取文件名
            String resources = "mybatis-config.xml";
            //创建流
            Reader reader = null;
            try {
                //读取mybatis-config.xml文件到reader对象中
                reader = Resources.getResourceAsReader(resources);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //初始化mybatis,创建SqlSessionFactory类的实例
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
            //创建session实例
            SqlSession session = sqlMapper.openSession();
            session.insert("insertcode",l);
            session.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
