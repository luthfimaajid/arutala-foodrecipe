package arutala.backend.bookrecipe.service;

import arutala.backend.bookrecipe.dto.request.AddRecipeRequest;
import arutala.backend.bookrecipe.model.*;
import arutala.backend.bookrecipe.repository.RecipeRepository;
import arutala.backend.bookrecipe.util.MinIo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@Slf4j
public class RecipeService {
    @Autowired
    private UserService userService;

    @Autowired
    private MasterService masterService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MinIo minIo;

    @Transactional
    public void addRecipe(MyUserDetails userDetails, AddRecipeRequest request) {
        User user = userService.getUserById(Integer.valueOf(userDetails.getId()));
        Level level = masterService.getLevelById(request.getLevel_id());
        Category category = masterService.getCategoryById(request.getCategory_id());

        String fileType = request.getImage().getContentType().split("/")[1];
        Long time = Instant.now().getEpochSecond();
        String imageFilename = String.format("%s_%s_%s.%s", request.getRecipe_name(), level.getLevelName(), time, fileType);

        minIo.upload(imageFilename, request.getImage());

        Recipe recipe = Recipe.builder()
                .user(user)
                .category(category)
                .level(level)
                .recipeName(request.getRecipe_name())
                .imageFilename(imageFilename)
                .timeCook(request.getTime_cook())
                .ingredient(request.getIngredient())
                .howToCook(request.getHow_to_cook())
                .isDeleted(Boolean.FALSE)
                .build();

        recipeRepository.save(recipe);
    }
}
