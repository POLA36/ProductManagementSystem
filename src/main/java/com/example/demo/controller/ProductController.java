package com.example.demo.controller;

import com.example.demo.dao.ProductDao;
import com.example.demo.helper.ProductHelper;
import com.example.demo.message.ResponMessage;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
@Controller
public class ProductController {
    @Autowired
    ProductDao productDao;

    @PostMapping("/regisProduct")
    public Product registerProducts (@RequestBody Product products){
        products.setExpiDate(new Date());
        productDao.save(products);
        return products;
    }

    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable int id){
       Product product= productDao.findById(id).orElse(null);
        return product;
    }

    @GetMapping("/getAllProduct")
    public List<Product> getProductById(){
        return productDao.findAll();
    }

    @PutMapping("/updateProduct/{id}")
    public Product updateProduct(@RequestBody Product newProduct,  @PathVariable int id){

        return productDao.findById(id)
               .map(product -> {
                  product.setName(newProduct.getName());
                  product.setPrice(newProduct.getPrice());
                  return productDao.save(product);
               }).orElseGet(()->{
                   newProduct.setId(id);
                   return productDao.save(newProduct);
               });
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable int id){
        productDao.deleteById(id);
        System.out.println("Product Deleted");
    }

    @GetMapping("/inventory")
    public List<Object[]> countCustomersByType() {
        return productDao.countProductByName();
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponMessage> uploadExcelfileData(@RequestParam("file") MultipartFile file){
        String  message = "";
        if (ProductHelper.hasExcelFormat(file)) {
            try {
                productDao.saveAll(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponMessage(message));

    }


}
