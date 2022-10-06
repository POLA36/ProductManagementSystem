package com.example.demo.controller;

import com.example.demo.dao.OrderDao;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderDao orderDao;

    @PostMapping("/makeOrder")
    public Order regierOder(@RequestBody Order orders){
        orders.setDateOrder(new Date());
        orderDao.save(orders);
        return orders;
    }

    @GetMapping("/getOrders")
    public List<Order> getAllOrders(){
        return orderDao.findAll();
    }


    @PutMapping("/updateOrder/{id}")
    public Order updateOrder(@RequestBody Order newOrder,  @PathVariable int id) {

        return orderDao.findById(id)
                .map(order -> {
                    order.setProductName(newOrder.getProductName());
                    order.setOrderStatus(newOrder.getOrderStatus());
                    return orderDao.save(order);
                }).orElseGet(() -> {
                    newOrder.setId(id);
                    return orderDao.save(newOrder);
                });
    }
}
