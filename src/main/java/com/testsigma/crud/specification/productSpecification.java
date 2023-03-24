package com.testsigma.crud.specification;

import com.testsigma.crud.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class productSpecification {

    public static Specification<Product> getSpec(String name){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if(name!=null && !name.isEmpty()) {
                    list.add(criteriaBuilder.like(root.get("name"),"%"+name+"%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        });
    }
}
