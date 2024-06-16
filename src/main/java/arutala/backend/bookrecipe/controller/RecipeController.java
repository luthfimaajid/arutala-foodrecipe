package arutala.backend.bookrecipe.controller;

import arutala.backend.bookrecipe.dto.BookRecipeDto;
import arutala.backend.bookrecipe.dto.request.AddRecipeRequest;
import arutala.backend.bookrecipe.model.MyUserDetails;
import arutala.backend.bookrecipe.service.RecipeService;
import arutala.backend.bookrecipe.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/book-recipe")
@Slf4j
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

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

    @PostMapping("/book-recipes")
    public ResponseEntity<Object> addBookRecipe(@ModelAttribute AddRecipeRequest request) {
        MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();

        recipeService.addRecipe(userDetails, request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/book-recipes/{recipe_id}")
    public ResponseEntity<Object> updateBookRecipe(@PathVariable("recipe_id") Integer recipeId, @RequestBody BookRecipeDto request) {
        // pass user id from jwt auth details
        return ResponseEntity.ok().build();
    }

    @PutMapping("/book-recipes/{recipe_id}/favorites")
    public ResponseEntity<Object> addRecipeToFavorites(@PathVariable("recipe_id") Integer recipeId) {
        // pass user id from jwt auth details
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/book-recipes/{recipe_id}")
    public ResponseEntity<Object> deleteBookRecipe(@PathVariable("recipe_id") Integer recipeId) {
        // pass user id from jwt auth details
        return ResponseEntity.ok().build();
    }
}
