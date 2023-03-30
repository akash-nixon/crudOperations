package com.testsigma.crud.controller;

import com.testsigma.crud.entity.Product;
import com.testsigma.crud.repository.productRepository;
import com.testsigma.crud.service.productService;
import com.testsigma.crud.specification.productSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
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
    @CrossOrigin("http://localhost:4200/")
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
        Specification<Product> specification = productSpecification.nameSpec(name);
        return productRepository.findAll(specification);
    }
    @GetMapping("/quanLess")
    public List<Product> quanLess(@RequestParam(value = "quantity",required = false)Integer quan){
        Specification<Product> specification = productSpecification.quanLessSpec(quan);
        return productRepository.findAll(specification);
    }
    @GetMapping("/quanMore")
    public List<Product> quanMore(@RequestParam(value = "quantity",required = false)Integer quan){
        Specification<Product> specification = productSpecification.quanMoreSpec(quan);
        return productRepository.findAll(specification);
    }
    @GetMapping("/priceLess")
    public List<Product> priceLess(@RequestParam(value = "price",required = false)Double price){
        Specification<Product> specification = productSpecification.priceLessSpec(price);
        return productRepository.findAll(specification);
    }
    @GetMapping("/priceMore")
    public List<Product> priceMore(@RequestParam(value = "price",required = false)Double price){
        Specification<Product> specification = productSpecification.priceMoreSpec(price);
        return productRepository.findAll(specification);
    }

    @GetMapping("/pagination/{pageNo}/{pageSize}")
    public Page<Product> pagination(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return productRepository.findAll(pageable);
    }

    @GetMapping("products/{field}/{order}")
    public List<Product> sort(@PathVariable String field,@PathVariable Boolean order){
        List<Product> filtered = productService.sort(field,order);
        return filtered;
    }
}
