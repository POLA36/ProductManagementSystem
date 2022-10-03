package com.example.demo.service;

import com.example.demo.dao.ProductDao;
import com.example.demo.helper.Helper;
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

     public void save(MultipartFile file){
         try{
             List<Product> products = Helper.convertExcelToListOfProduct(file.getInputStream());
             this.productDao.saveAll(products);
         }catch (IOException e){
             e.printStackTrace();
         }
     }
     public  List<Product> getAllProducts(){
         return  this.productDao.findAll();
     }
}
