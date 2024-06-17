package arutala.backend.bookrecipe.controller;

import arutala.backend.bookrecipe.dto.RecipeDto;
import arutala.backend.bookrecipe.dto.request.AddRecipeRequest;
import arutala.backend.bookrecipe.dto.request.GetRecipesQueryParams;
import arutala.backend.bookrecipe.model.MyUserDetails;
import arutala.backend.bookrecipe.model.Recipe;
import arutala.backend.bookrecipe.service.RecipeService;
import arutala.backend.bookrecipe.service.UserDetailsServiceImpl;
import arutala.backend.bookrecipe.util.ResponseHandler;
import arutala.backend.bookrecipe.util.ResponseMessage;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-recipe")
@Slf4j
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/my-recipes")
    public ResponseEntity<Object> getMyBookRecipes(GetRecipesQueryParams params) {
        MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();
        params.setUserId(userDetails.getId());

        List<RecipeDto> bookRecipes = recipeService.getBookRecipes(params);

        return ResponseHandler.ok(ResponseMessage.Success.DEFAULT, bookRecipes);
    }

    @GetMapping("/book-recipes")
    public ResponseEntity<Object> getBookRecipes(GetRecipesQueryParams params) {
        List<RecipeDto> bookRecipes = recipeService.getBookRecipes(params);

        return ResponseHandler.ok(ResponseMessage.Success.DEFAULT, bookRecipes);
    }

    @GetMapping("/book-recipes/{recipe_id}")
    public ResponseEntity<Object> getBookRecipe(@PathVariable("recipe_id") Integer recipeId) {
        MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();

        RecipeDto recipe = recipeService.getRecipeDetailById(userDetails, recipeId);

        return ResponseHandler.ok(ResponseMessage.Success.DEFAULT, recipe);
    }

    @PostMapping("/book-recipes")
    public ResponseEntity<Object> addBookRecipe(@Valid @ModelAttribute AddRecipeRequest request) {
        MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();

        recipeService.addRecipe(userDetails, request);

        return ResponseHandler.ok(ResponseMessage.Success.DEFAULT);
    }

    @PutMapping("/book-recipes/{recipe_id}")
    public ResponseEntity<Object> updateBookRecipe(@PathVariable("recipe_id") Integer recipeId, @Valid @ModelAttribute AddRecipeRequest request) {
        MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();

        Recipe recipe = recipeService.updateRecipe(userDetails, recipeId, request);

        return ResponseHandler.ok(String.format(ResponseMessage.Success.RECIPE_UPDATED, recipe.getRecipeName()));
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
        MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();

        Recipe recipe = recipeService.deleteRecipe(userDetails, recipeId);

        return ResponseHandler.ok(String.format(ResponseMessage.Success.RECIPE_DELETED, recipe.getRecipeName()), 0);
    }
}
