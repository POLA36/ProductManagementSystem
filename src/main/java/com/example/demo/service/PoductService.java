package com.example.demo.service;

import com.example.demo.dao.ProductDao;
import com.example.demo.helper.ProductHelper;
import com.example.demo.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PoductService {

     Product product = new Product();
     private  ProductDao productDao;

     public Product saveProduct(Product product){
         productDao.save(product);
         return  product;
     }

    public void saveExcelProduct(MultipartFile file){
        try{
            List<Product> tutorials = ProductHelper.excelToDatabase(file.getInputStream());
            productDao.saveAll(tutorials);
        }catch (IOException e){
            throw new RuntimeException("fail to store excel data" +e.getMessage());
        }
    }
    public List<Product> getAllTutorials(){
        return productDao.findAll();
    }
}
