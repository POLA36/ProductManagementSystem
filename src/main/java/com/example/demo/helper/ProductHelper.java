package com.example.demo.helper;


import com.example.demo.models.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "name", "price", "quantity", "expiDate"};
    static String SHEET = "Product";

    //Verify if the file is an excel file

    public static boolean hasExcelFormat(MultipartFile file){
        if (!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    public static List<Product> excelToDatabase(InputStream is){
        try{
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.iterator().next();
            Iterator<Row> productRows = sheet.iterator();

            List<Product> excelProduct = new ArrayList<Product>();

            int rowNumber = 0;

            while(productRows.hasNext()){
                Row productCurrentRow = productRows.next();

                if (rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellProductInRow = productCurrentRow.iterator();
                Product newProduct = new Product();

                int cellProductIndex = 0;

                while (cellProductInRow.hasNext()){
                    Cell productCurrentCell = cellProductInRow.next();

                    switch (cellProductIndex){
                        case 0:
                            newProduct.setId((int) productCurrentCell.getNumericCellValue());
                            break;
                        case 1:
                            newProduct.setName(productCurrentCell.getStringCellValue());
                            break;
                        case 2:
                            newProduct.setPrice(productCurrentCell.getColumnIndex());
                            break;
                        case 3:
                            newProduct.setQuantity(productCurrentCell.getColumnIndex());
                            break;
                        case 4:
                            newProduct.setExpiDate(productCurrentCell.getDateCellValue());
                            break;
                        default:
                            break;
                    }
                    cellProductIndex++;
                }
                excelProduct.add(newProduct);
            }
            workbook.close();
            return excelProduct;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse your excel file"+ e.getMessage());
        }
    }
}
