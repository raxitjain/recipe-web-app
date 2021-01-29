package com.ernstyoung.recipewebapp.repositories;

import com.ernstyoung.recipewebapp.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByCategoryName(String description);

}
