package com.example.homework27;

import com.example.homework27.Model.Product;
import com.example.homework27.Model.User;
import com.example.homework27.Repository.ProductRepository;
import com.example.homework27.Repository.UserRepository;
import com.example.homework27.Service.AuthService;
import com.example.homework27.Service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @InjectMocks
    AuthService authService;
    @InjectMocks
    ProductService productService;
    @Mock
    UserRepository userRepository;
    @Mock
    ProductRepository productRepository;

    User user1,user2,user3;
    Product product1,product2,product3;
    List<Product> products;
    List<User> users;
    @BeforeEach
    void setUp() {
        user1  =new User(null,"SAUD" , "12345" , "ADMIN" ,null);
        user2 =new User(null,"saad" , "12345" , "ADMIN" ,null);
        user3 =new User(null,"ali" , "12345" , "ADMIN" ,null);


        product1= new Product(null , "product1", 500,null );
        product2= new Product(null , "product2", 500,null );
        product3= new Product(null , "product3", 500,null );

        products=new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @Test
    public void getAllProduct(){
        when(productRepository.findAll()).thenReturn(products);
        List<Product> productList=productService.getAllProduct();
        Assertions.assertEquals(3,productList.size());
        verify(productRepository,times(1)).findAll();

    }
    @Test
    public void AddTodoTest(){

        productService.addProduct(product3);
        verify(productRepository,times(1)).save(product3);
    }

    @Test
    public void getAllUsers(){
        when(userRepository.findAll()).thenReturn(users);
        List<User> userList=authService.getAll();
        Assertions.assertEquals(3,userList.size());
        verify(userRepository,times(1)).findAll();

    }
    @Test
    public void updateProductTest(){

        when(productRepository.findProductById(product1.getId())).thenReturn(product1);


        productService.updateProduct(product1.getId(),product2);

        verify(productRepository,times(1)).findProductById(product1.getId());
        verify(productRepository,times(1)).save(product1);

    }

    @Test
    public void updateUserTest(){


        when(userRepository.findUsersById(user1.getId())).thenReturn(user1);

        authService.updateUser(user1.getId(),user2);


        verify(userRepository,times(1)).findUsersById(user1.getId());
        verify(userRepository,times(1)).save(user1);

    }

}
