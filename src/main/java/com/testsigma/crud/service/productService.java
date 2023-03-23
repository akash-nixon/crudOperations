package com.testsigma.crud.service;

import com.testsigma.crud.entity.Product;
import com.testsigma.crud.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {
    @Autowired
    private productRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> bulkSave(List<Product> products){
        return productRepository.saveAll(products);
    }

    public List<Product> bulkGet(){
        return productRepository.findAll();
    }

    public Product getProductById(int id){
        return productRepository.findById(id).orElse(null);
    }

    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }

    public String deleteProduct(int id){
        productRepository.deleteById(id);
        return "deleted id:"+id;
    }

    public Product updateProduct(Product product){
        Product existProduct = productRepository.findById(product.getId()).orElse(null);
        existProduct.setName(product.getName());
        existProduct.setQuantity(product.getQuantity());
        existProduct.setPrice(product.getPrice());
        return productRepository.save(existProduct);
    }
}
