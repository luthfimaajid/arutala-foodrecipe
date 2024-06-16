package arutala.backend.bookrecipe.controller;

import arutala.backend.bookrecipe.dto.BookRecipeDto;
import arutala.backend.bookrecipe.dto.request.AddRecipeRequest;
import arutala.backend.bookrecipe.dto.request.GetRecipesQueryParams;
import arutala.backend.bookrecipe.model.MyUserDetails;
import arutala.backend.bookrecipe.model.Recipe;
import arutala.backend.bookrecipe.service.RecipeService;
import arutala.backend.bookrecipe.service.UserDetailsServiceImpl;
import arutala.backend.bookrecipe.util.ResponseHandler;
import arutala.backend.bookrecipe.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
    public ResponseEntity<Object> getBookRecipes(GetRecipesQueryParams params) {
        log.info(params.toString());
        MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();

        List<BookRecipeDto> bookRecipes = recipeService.getBookRecipes(userDetails, params);

        return ResponseHandler.ok(ResponseMessage.Success.DEFAULT, bookRecipes);
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
        MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();

        Recipe recipe = recipeService.addRecipeToFavorites(userDetails, recipeId);
        String message = ResponseMessage.Success.ADDED_TO_FAVORITE;
        if (!recipe.getFavoriteFood().getIsFavorite()) {
            message = ResponseMessage.Success.REMOVED_FROM_FAVORITE;
        }

        return ResponseHandler.ok(String.format(message, recipe.getRecipeName()), 1);
    }

    @DeleteMapping("/book-recipes/{recipe_id}")
    public ResponseEntity<Object> deleteBookRecipe(@PathVariable("recipe_id") Integer recipeId) {
        // pass user id from jwt auth details
        return ResponseEntity.ok().build();
    }
}
