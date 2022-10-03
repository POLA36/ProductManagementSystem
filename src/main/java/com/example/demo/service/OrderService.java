package com.example.demo.service;

import com.example.demo.dao.OrderDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    Order oder = new Order();

    private OrderDao oderDao;

    public Order saveOrder(Order oders){
       oderDao.save(oders);
       return oders;
    }
}
