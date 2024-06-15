package arutala.backend.bookrecipe.controller;

import arutala.backend.bookrecipe.dto.BookRecipeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/book-recipe")
public class RecipeController {
    @GetMapping("/my-recipes")
    public ResponseEntity<Object> getMyBookRecipes() {
        // pass user id from jwt auth details
        return ResponseEntity.ok().build();
    }

    @GetMapping("/book-recipes")
    public ResponseEntity<Object> getBookRecipes(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/book-recipes/{recipe_id}")
    public ResponseEntity<Object> getBookRecipe(@PathVariable("recipe_id") String recipeId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("book-recipes")
    public ResponseEntity<Object> addBookRecipe(@RequestBody BookRecipeDto request) {
        // pass user id from jwt auth details
        return ResponseEntity.ok().build();
    }

    @PutMapping("book-recipes/{recipe_id}")
    public ResponseEntity<Object> updateBookRecipe(@PathVariable("recipe_id") Integer recipeId, @RequestBody BookRecipeDto request) {
        // pass user id from jwt auth details
        return ResponseEntity.ok().build();
    }

    @PutMapping("/book-recipes/{recipe_id}/favorites")
    public ResponseEntity<Object> addRecipeToFavorites(@PathVariable("recipe_id") Integer recipeId) {
        // pass user id from jwt auth details
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("book-recipes/{recipe_id}")
    public ResponseEntity<Object> deleteBookRecipe(@PathVariable("recipe_id") Integer recipeId) {
        // pass user id from jwt auth details
        return ResponseEntity.ok().build();
    }
}
