package com.testsigma.crud.specification;

import com.testsigma.crud.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class productSpecification {

    public static Specification<Product> nameSpec(String name){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if(name!=null && !name.isEmpty()) {
                    list.add(criteriaBuilder.like(root.get("name"),"%"+name+"%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        });
    }
    public static Specification<Product> quanLessSpec(Integer quan){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if(quan!=null) {
                list.add(criteriaBuilder.lessThan(root.get("quantity"),quan));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        });
    }

    public static Specification<Product> quanMoreSpec(Integer quan){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if(quan!=null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("quantity"),quan));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        });
    }

    public static Specification<Product> priceLessSpec(Double price){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if(price!=null) {
                list.add(criteriaBuilder.lessThan(root.get("price"),price));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        });
    }

    public static Specification<Product> priceMoreSpec(Double price){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if(price!=null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),price));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        });
    }
}
