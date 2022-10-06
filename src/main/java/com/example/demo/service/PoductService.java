package com.example.demo.service;

import com.example.demo.dao.ProductDao;
import com.example.demo.helper.ProductHelper;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PoductService {
    @Autowired
     ProductDao productDao;

     public Product saveProduct(Product product){
         productDao.save(product);
         return  product;
     }

     public void savePrductFile(MultipartFile file){
         try {
             List<Product> productList  = ProductHelper.excelToDatabase(file.getInputStream());
             productDao.saveAll(productList);
         } catch (Exception e) {
             throw new RuntimeException("Fail to store excel file data" + e.getMessage());
         }
     }

     public List<Product> getAllProducts(){
         return  productDao.findAll();
     }
}
