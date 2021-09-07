import com.zhangkai.www.xuankesystem.domain.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateExcel {
    /**
     * 获取对象的属性名数组
     * @param beanClass Class 对象，用于获取该类的信息
     * @return 该类的所有属性名的数组
     */
    private static String[] getFieldsName(Class beanClass){
        Field[] fields = beanClass.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 使用这个类去创建Excel表格
     */
        /**
         * 使用这个方法,根据传入的参数生成返回一个表格
         * @param data 需要转成表格的数据
         */
        public static void createExcelByBeans(List<User> data) throws Exception{
            try {
                //创建一个File对象,用来最后写入数据到这个文件
                File excelFile = new File("/xuankesystem/src/main/resources/writeExcel.xlsx");
                FileOutputStream excelOutputStream = new FileOutputStream(excelFile);
                //开始创建Excel文件对象
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet();
                //生成表头
                Row thead = sheet.createRow(0);
                String[] fieldsName = getFieldsName(data.get(0).getClass());
                for (int i = 0; i < fieldsName.length; i++) {
                    Cell cell = thead.createCell(i);
                    cell.setCellValue(fieldsName[i]);
                }
                //创建表格
                Method[] methods = new Method[fieldsName.length];
                for (int i = 0; i < data.size(); i++) {
                    Row row = sheet.createRow(i+1);
                    Object obj = data.get(i);
                    for (int j = 0; j < fieldsName.length; j++) {
                        //加载第一行数据时，初始化所有属性的getter方法
                        if(i == 0){
                            String fieldName = fieldsName[j];
                            methods[j] = obj.getClass().getMethod("get" +
                                    fieldName.substring(0,1).toUpperCase() +
                                    fieldName.substring(1));
                        }
                        Cell cell = row.createCell(j);
                        Object value = methods[j].invoke(obj);
                        //注意判断 value 值是否为空
                        if(value == null){
                            value = "无";
                        }
                        cell.setCellValue(value.toString());
                    }
                }
                //将数据写入文件
                workbook.write(excelOutputStream);
                //关闭流
                excelOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Test
    public void readExcel() throws Exception {
            String filePath="/xuankesystem/src/main/resources/writeExcel.xlsx";
        FileInputStream fis = new FileInputStream(
                new File(filePath));
        //打开需要读取的文件
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //按照SHEET的名称读取一个电子表格
        XSSFSheet sheet = workbook.getSheet("Sheet0");
        //获取一个行的迭代器
        Iterator<Row> rowIterator = sheet.rowIterator();
        Row row=null;//自己添加
            int index=0;
        while(rowIterator.hasNext()) {
            row = (XSSFRow) rowIterator.next();
            //获取单元格迭代器
            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()) {
                index++;
                Cell cell = cellIterator.next();
                switch(cell.getCellType()) {
                    case NUMERIC:
                        double val = cell.getNumericCellValue();
                        System.out.println(val+"\t\t");
                        break ;
                    case STRING:
                        String str = cell.getStringCellValue();
                        System.out.println(str+"\t\t");

                }
                System.out.println(index);
            }
            System.out.println();
        }
        fis.close();
    }
@Test
    public  void rExcel() throws Exception {
        List<User> list=new ArrayList<>();
        String filePath="/xuankesystem/src/main/resources/ReadExcel.xlsx";
        FileInputStream fis = new FileInputStream(
                new File(filePath));
        //打开需要读取的文件
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //按照SHEET的名称读取一个电子表格
        XSSFSheet sheet = workbook.getSheet("Sheet0");
        //获取一个行的迭代器
        Iterator<Row> rowIterator = sheet.rowIterator();
        Row row=null;//自己添加
        int index=0;
        int yu=index%9;
        User user=null;
        while(rowIterator.hasNext()) {
            user=new User();
            row = (XSSFRow) rowIterator.next();
            //获取单元格迭代器
            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()) {
                index++;
                Cell cell = cellIterator.next();
                if(index<10) continue;
                switch(cell.getCellType()) {
                    /*case NUMERIC:
                        double val = cell.getNumericCellValue();
                        switch(index%9){
                            case 1:{
                                user.setId((int)val);
                                break;
                            }
                            case 3:{
                                user.setPassword(Double.toString(val));
                                break;
                            }
                            case 6:{
                                user.setXuehao(Double.toString(val));
                                break;
                            }
                        }
                        break ;*/
                    case STRING:
                        String str = cell.getStringCellValue();
                        switch(index%9){
                            case 0:{
                                user.setAdmin(Boolean.getBoolean(str));
                                break;
                            }
                            case 1:{
                                user.setId(Integer.parseInt(str));
                                break;
                            }
                            case 2:{
                                user.setUsername(str);
                                break;
                            }
                            case 3:{
                                user.setPassword(str);
                                break;
                            }
                            case 4:{
                                user.setName(str);
                                break;
                            } case 5:{
                                user.setSex(str);
                                break;
                            }case 6:{
                                user.setXuehao(str);
                                break;
                            }
                            case 7:{
                                user.setZhuanye(str);
                                break;
                            }
                            case 8:{
                                user.setBanji(str);
                                break;
                            }
                        }
                        //System.out.print(str+"\t\t");
                }
                list.add(user);
            }
            //System.out.println();
        }
        fis.close();
    System.out.println(list);
    }




























    /**
     * 使用这个方法,根据传入的参数生成返回一个表格
     * @param
     */
    /*@Test
    public void createExcel(){
        try {
            List<Integer> data=new ArrayList<>();
            data.add(7);
            data.add(10);
            data.add(11);
            System.out.println(data);
            //创建一个File对象,用来最后写入数据到这个文件
            File excelFile = new File("/xuankesystem/src/main/resources/writeExcel.xlsx");
            FileOutputStream excelOutputStream = new FileOutputStream(excelFile);
            //开始创建Excel文件对象
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet();
            // 限定列数
            int cols = 10;
            int rows = data.size() / cols;
            int index = 0;
            for (int rowNum = 0; rowNum < rows; rowNum++) {
                Row row = sheet.createRow(rowNum);
                for (int colNum = 0; colNum < cols; colNum++) {
                    Cell cell = row.createCell(colNum);
                    cell.setCellValue(data.get(index++).toString());
                }
            }
            //将数据写入文件
            workbook.write(excelOutputStream);
            //关闭流
            excelOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
