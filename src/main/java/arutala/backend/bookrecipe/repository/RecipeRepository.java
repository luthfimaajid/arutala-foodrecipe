package arutala.backend.bookrecipe.repository;

import arutala.backend.bookrecipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
