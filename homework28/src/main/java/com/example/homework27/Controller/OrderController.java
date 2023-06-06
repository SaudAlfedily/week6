package com.example.homework27.Controller;

import com.example.homework27.Model.Order;
import com.example.homework27.Model.User;
import com.example.homework27.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
//Customer
    @GetMapping("/get")
    public ResponseEntity getAllCustomerOrders(@AuthenticationPrincipal User user) {

        List<Order> orders = orderService.getAllOrder(user.getId());
        return ResponseEntity.status(200).body(orders);

    }
//Customer
    @PostMapping("/add/{productid}")
    public ResponseEntity addOrder(@AuthenticationPrincipal User user, @PathVariable Integer productid, @RequestBody @Valid Order order) {
        orderService.addOrder(user.getId(), productid, order);
        return ResponseEntity.status(200).body("order added");


    }
//admin only can update
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer  id, @RequestBody @Valid Order order){
        orderService.updateOrder(id,order);
        return ResponseEntity.status(200).body("order updated");

    }
// customer and admin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal User user,@PathVariable Integer  id){
        orderService.deleteOrder(user.getId(), id);
        return ResponseEntity.status(200).body("order deleted");

    }
//admin
    @PutMapping("/changestatus/{id}/{status}")
    public ResponseEntity changeStatus(@PathVariable Integer id ,@PathVariable String status){
       orderService.changeStatus(id,status);
        return ResponseEntity.status(200).body("status changed");


    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getOrderById(@PathVariable Integer id){
       Order order= orderService.getOrderByID(id);
        return ResponseEntity.status(200).body(order);
    }

}
