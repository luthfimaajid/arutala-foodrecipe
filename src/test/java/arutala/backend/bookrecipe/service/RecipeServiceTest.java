package arutala.backend.bookrecipe.service;

import arutala.backend.bookrecipe.dto.request.AddRecipeRequest;
import arutala.backend.bookrecipe.model.*;
import arutala.backend.bookrecipe.repository.RecipeRepository;
import arutala.backend.bookrecipe.util.MinIo;
import com.google.common.net.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {
    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private UserService userService;

    @Mock
    private MasterService masterService;

    @Mock
    private MinIo minIo;

    @Mock
    private RecipeRepository recipeRepository;

    @Test
    void addRecipe() {
        MyUserDetails mockUserDetails = MyUserDetails.builder()
                .id(1)
                .build();

        User user = User.builder()
                .id(1)
                .build();

        Level mockLevel = Level.builder()
                .id(1)
                .levelName("Master")
                .build();

        Category mockCategory = Category.builder()
                .id(1)
                .categoryName("Lunch")
                .build();

        AddRecipeRequest mockAddRecipeRequest = AddRecipeRequest.builder()
                .recipe_name("Seblak")
                .category_id(1)
                .level_id(1)
                .how_to_cook("how to cook")
                .ingredient("ingredient")
                .image_filename(new MockMultipartFile("file", "recipe_image.png", String.valueOf(MediaType.PNG), "recipe_image".getBytes()))
                .time_cook(10)
                .build();

        Recipe mockRecipe = Recipe.builder()
                .recipeName("Seblak")
                .level(mockLevel)
                .category(mockCategory)
                .howToCook("how to cook")
                .ingredient("ingredient")
                .isDeleted(Boolean.FALSE)
                .timeCook(10)
                .imageFilename("Seblak_Master_999.png")
                .build();

        Mockito.when(userService.getUserById(mockUserDetails.getId())).thenReturn(user);
        Mockito.when(masterService.getCategoryById(mockUserDetails.getId())).thenReturn(mockCategory);
        Mockito.when(masterService.getLevelById(mockLevel.getId())).thenReturn(mockLevel);
        Mockito.when(recipeRepository.save(Mockito.any(Recipe.class))).thenReturn(mockRecipe);
//        Mockito.when(Instant.now().getEpochSecond()).thenReturn(999L);

        Recipe recipe = recipeService.addRecipe(mockUserDetails, mockAddRecipeRequest);


        assertNotNull(recipe);
        assertEquals("Seblak", recipe.getRecipeName());
        assertEquals(1, recipe.getCategory().getId());
        assertEquals(1, recipe.getLevel().getId());
    }

    @Test
    void getBookRecipes() {
    }

    @Test
    void addRecipeToFavorites() {
    }

    @Test
    void getRecipeDetailById() {
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void deleteRecipe() {
    }

}