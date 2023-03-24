package com.testsigma.crud.controller;

import com.testsigma.crud.entity.Product;
import com.testsigma.crud.repository.productRepository;
import com.testsigma.crud.service.productService;
import com.testsigma.crud.specification.productSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productController {
    @Autowired
    private productService productService;
    @Autowired
    private productRepository productRepository;
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    @PostMapping("/bulkadd")
    public List<Product> bulkAdd(@RequestBody List<Product> products){
        return productService.bulkSave(products);
    }
    @GetMapping("/products")
    public List<Product> findAll(){
        return productService.bulkGet();
    }
    @GetMapping("/productbyid/{id}")
    public Product findProductById(@PathVariable int id){
        return productService.getProductById(id);
    }
    @GetMapping("/productbyname/{name}")
    public Product findProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }

    @PutMapping("/update")
    public Product update(@RequestBody Product product){
        return productService.updateProduct(product);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<Product> list(@RequestParam(value = "name",required = false)String name){
        Specification<Product> specification = productSpecification.getSpec(name);
        return productRepository.findAll(specification);
    }
}
