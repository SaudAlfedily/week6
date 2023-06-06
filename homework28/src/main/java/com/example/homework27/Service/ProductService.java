package com.example.homework27.Service;

import com.example.homework27.ApiException.ApiException;
import com.example.homework27.Model.Product;
import com.example.homework27.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProduct(){
        List<Product> products =  productRepository.findAll();
        return products;

    }

    public void addProduct(Product product){
        productRepository.save(product);


    }


    public void updateProduct(Integer id, Product product){
        Product oldProduct = productRepository.findProductById(id);
        if (oldProduct==null){
            throw new ApiException("product not found");

        }
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());

        productRepository.save(oldProduct);

    }
    public void deleteProduct(Integer id){
        Product oldProduct = productRepository.findProductById(id);
        if (oldProduct==null){
            throw new ApiException("product not found");

        }
        productRepository.delete(oldProduct);
    }

    public Product getProductByID(Integer id) {
        Product product = productRepository.findProductById(id);
        if (product==null){
            throw new ApiException("product not found");

        }
        return  product;
    }
}
