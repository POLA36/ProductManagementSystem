package com.example.demo.helper;

import com.example.demo.models.Product;
import net.bytebuddy.asm.Advice;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Helper {
    public static boolean checkExcelFormat(MultipartFile file){
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return  true;
        }else{
            return false;
        }
    }

    //convert excel to list of product

    public static List<Product> convertExcelToListOfProduct(InputStream is){
        List<Product> list = new ArrayList<>();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet productData = workbook.getSheet("productData");

            int rownNumber = 0;
            Iterator<Row> iterator = productData.iterator();

            while(iterator.hasNext()){
                Row row = iterator.next();
                if (rownNumber == 0){
                    rownNumber++;
                    continue;
                }

                Iterator<Cell>  cells = row.iterator();
                int cid = 0;
                Product product = new Product();
                while(cells.hasNext()){
                    Cell cell = cells.next();
                    switch (cid){
                        case 0:
                           product.setId((int)cell.getNumericCellValue());
                           break;
                        case 1:
                            product.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            product.setPrice((double)cell.getNumericCellValue());
                            break;
                        case 3:
                            product.setQuantity((int)cell.getNumericCellValue());
                            break;
                        case 4:
                            product.setExpiDate(cell.getDateCellValue());
                            break;
                    }
                    cid++;
                }
                list.add(product);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  list;
    }
}
