package com.example.homework27.Repository;

import com.example.homework27.Model.Order;
import com.example.homework27.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findOrderById(Integer id);
    List<Order> findOrderByUserId(Integer userid);
}
