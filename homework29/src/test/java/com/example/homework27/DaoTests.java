package com.example.homework27;

import com.example.homework27.Model.Order;
import com.example.homework27.Model.Product;
import com.example.homework27.Model.User;
import com.example.homework27.Repository.OrderRepository;
import com.example.homework27.Repository.ProductRepository;
import com.example.homework27.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    User user;
    Product product;
    Order order;

    @BeforeEach
    void setUp() {
       user  =new User(null,"SAUD" , "12345" , "ADMIN" ,null);
        product = new Product(null , "product1", 500,null );
        order = new Order(null , 50, 200 , LocalDate.now(),"NEW",null,null);

    }
    @Test
    public void findUserById(){
        userRepository.save(user);
        User user1=userRepository.findUsersById(user.getId());
        Assertions.assertThat(user1).isEqualTo(user);
    }
    @Test
    public void findUserByUsername(){
        userRepository.save(user);
        User user1=userRepository.findUsersByUsername(user.getUsername());
        Assertions.assertThat(user1).isEqualTo(user);
    }

    @Test
    public void findProductById(){
        productRepository.save(product);
        Product product1=productRepository.findProductById(product.getId());
        Assertions.assertThat(product1).isEqualTo(product);
    }

    @Test
    public void findOrderById(){
        orderRepository.save(order);
        Order order1=orderRepository.findOrderById(order.getId());
        Assertions.assertThat(order1).isEqualTo(order);
    }


}
