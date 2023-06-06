package com.example.homework27.Service;

import com.example.homework27.ApiException.ApiException;
import com.example.homework27.Model.Order;
import com.example.homework27.Model.Product;
import com.example.homework27.Model.User;
import com.example.homework27.Repository.OrderRepository;
import com.example.homework27.Repository.ProductRepository;
import com.example.homework27.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Order> getAllOrder(Integer userid) {
        List<Order> orders = orderRepository.findOrderByUserId(userid);
        return orders;

    }
    //Customer
    public void addOrder(Integer userId,Integer productid,Order order) {

        Product product=productRepository.findProductById(productid);
        User user = userRepository.findUsersById(userId);
        order.setProduct(product);
        order.setUser(user);

        order.setTotalPrice(product.getPrice()*order.getQuantity());
        order.setStatus("NEW");
        orderRepository.save(order);

    }


    public void updateOrder(Integer id, Order order) {
        Order oldOrder = orderRepository.findOrderById(id);
        if (oldOrder == null) {
            throw new ApiException("Order not found");

        }
        oldOrder.setStatus(order.getStatus());
        oldOrder.setQuantity(order.getQuantity());
        oldOrder.setTotalPrice(order.getTotalPrice());
        oldOrder.setDateReceived(order.getDateReceived());

        orderRepository.save(oldOrder);

    }

    public void deleteOrder(Integer userid,Integer id) {

        Order oldOrder = orderRepository.findOrderById(id);
        if (oldOrder.getUser().getId()!=userid||oldOrder == null) {
            throw new ApiException("Order not found or you dont own this order");

        }
        if (oldOrder.getStatus().equalsIgnoreCase("inProgress") || oldOrder.getStatus().equalsIgnoreCase("completed")) {
            throw new ApiException("Order inProgress or completed");

        }
        orderRepository.delete(oldOrder);
    }
// ADMIN can put Status to  NEW or INPROGRESS or COMPLETED  if he tried something else the method will throw exception "Could not commit JPA transaction"
    public void changeStatus(Integer id, String status) {
        Order oldOrder = orderRepository.findOrderById(id);
        if (oldOrder == null) {
            throw new ApiException("Order not found");

        }
        oldOrder.setStatus(status);
        orderRepository.save(oldOrder);
    }

    public Order getOrderByID(Integer id){
        Order order =orderRepository.findOrderById(id);
        return order;


    }
}
