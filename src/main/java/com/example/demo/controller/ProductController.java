package com.example.demo.controller;

import com.example.demo.dao.ProductDao;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductDao productDao;

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

    @GetMapping("/getProductLike")
    public void getProductByNameLike(){
        List<Product> products = productDao.findByNameLike("product 1");
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
}
