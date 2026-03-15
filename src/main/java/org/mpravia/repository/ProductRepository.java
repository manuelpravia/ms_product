package org.mpravia.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.mpravia.repository.entity.Product;

import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

   public Product findByCode(String code) {
        return find("code", code).firstResult();
   }

   public List<Product> findByCodes(List<String> codes) {
        return list("code in ?1", codes);
   }
}
