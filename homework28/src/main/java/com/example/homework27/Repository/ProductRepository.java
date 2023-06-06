package com.example.homework27.Repository;

import com.example.homework27.Model.Product;
import com.example.homework27.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findProductById(Integer id);
}
