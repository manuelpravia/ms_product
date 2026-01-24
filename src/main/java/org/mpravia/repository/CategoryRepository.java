package org.mpravia.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.mpravia.repository.entity.Category;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {

    public Category findCategoryByName(String name) {
        return find("name", name).firstResult();
    }
}
