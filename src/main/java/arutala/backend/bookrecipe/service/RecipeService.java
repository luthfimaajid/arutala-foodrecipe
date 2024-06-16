package arutala.backend.bookrecipe.service;

import arutala.backend.bookrecipe.dto.RecipeDto;
import arutala.backend.bookrecipe.dto.request.AddRecipeRequest;
import arutala.backend.bookrecipe.dto.request.GetRecipesQueryParams;
import arutala.backend.bookrecipe.model.*;
import arutala.backend.bookrecipe.repository.RecipeRepository;
import arutala.backend.bookrecipe.repository.specification.RecipeSpecification;
import arutala.backend.bookrecipe.util.MinIo;
import arutala.backend.bookrecipe.util.ResponseMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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
        User user = userService.getUserById(userDetails.getId());
        Level level = masterService.getLevelById(request.getLevel_id());
        Category category = masterService.getCategoryById(request.getCategory_id());

        String fileType = request.getImage_filename().getContentType().split("/")[1];
        Long time = Instant.now().getEpochSecond();
        String imageFilename = String.format("%s_%s_%s.%s", request.getRecipe_name(), level.getLevelName(), time, fileType);

        minIo.upload(imageFilename, request.getImage_filename());

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

    public List<RecipeDto> getBookRecipes(GetRecipesQueryParams params) {
        try {
            Specification<Recipe> specification = Specification.where(RecipeSpecification.notDeleted());
            Pageable pageable = PageRequest.of(0, 10);

            if (params != null) {
                if (params.getUserId() != null) {
                    specification = specification.and(RecipeSpecification.userId(params.getUserId()));
                }

                if (params.getRecipeName() != null) {
                    specification = specification.and(RecipeSpecification.recipeNameLike(params.getRecipeName()));
                }

                if (params.getLevelId() != null) {
                    specification = specification.and(RecipeSpecification.levelId(params.getLevelId()));
                }

                if (params.getCategoryId() != null) {
                    specification = specification.and(RecipeSpecification.categoryId(params.getCategoryId()));
                }

                if (params.getSortBy() != null) {
                    if (!params.getSortBy().isEmpty()) {
                        specification = RecipeSpecification.orderBy(specification, params.getSortBy());
                    }
                } else {
                    params.setSortBy(List.of("recipeName", "asc"));
                    specification = RecipeSpecification.orderBy(specification, params.getSortBy());
                }

                if (params.getPageSize() != null && params.getPageSize() > 0 && params.getPageNumber() != null && params.getPageNumber() >= 0) {
                    pageable = PageRequest.of(params.getPageNumber(), params.getPageSize());
                }
            }

            return recipeRepository.findAll(specification, pageable).stream().map(RecipeDto::createGetRecipesDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public Recipe addRecipeToFavorites(MyUserDetails userDetails, Integer recipeId) {
        Recipe recipe = recipeRepository.findByIdAndUserIdAndIsDeleted(recipeId, userDetails.getId(), Boolean.FALSE).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.Failed.RECIPE_NOT_FOUND));

        if (recipe.getFavoriteFood() != null) {
            recipe.getFavoriteFood().setIsFavorite(!recipe.getFavoriteFood().getIsFavorite());
        } else {
            FavoriteFood favoriteFood = new FavoriteFood();
            favoriteFood.setIsFavorite(Boolean.TRUE);
            favoriteFood.setRecipe(recipe);
            favoriteFood.setUser(recipe.getUser());

            recipe.setFavoriteFood(favoriteFood);
        }

        return recipeRepository.save(recipe);
    }

    public RecipeDto getRecipeDetailById(MyUserDetails userDetails, Integer recipeId) {
        Recipe recipe = recipeRepository.findByIdAndUserIdAndIsDeleted(recipeId, userDetails.getId(), Boolean.FALSE).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.Failed.RECIPE_NOT_FOUND));
        return RecipeDto.createGetRecipeDetailDto(recipe);
    }

    public Recipe updateRecipe(MyUserDetails userDetails, Integer recipeId, AddRecipeRequest request) {
        Recipe recipe = recipeRepository.findByIdAndUserIdAndIsDeleted(recipeId, userDetails.getId(), Boolean.FALSE).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.Failed.RECIPE_NOT_FOUND));

        Level level = masterService.getLevelById(request.getLevel_id());
        Category category = masterService.getCategoryById(request.getCategory_id());

        String fileType = request.getImage_filename().getContentType().split("/")[1];
        Long time = Instant.now().getEpochSecond();
        String imageFilename = String.format("%s_%s_%s.%s", request.getRecipe_name(), level.getLevelName(), time, fileType);

        minIo.upload(imageFilename, request.getImage_filename());

        recipe.setRecipeName(request.getRecipe_name());
        recipe.setCategory(category);
        recipe.setLevel(level);
        recipe.setImageFilename(imageFilename);
        recipe.setTimeCook(request.getTime_cook());
        recipe.setHowToCook(request.getHow_to_cook());
        recipe.setIngredient(request.getIngredient());

        return recipeRepository.save(recipe);
    }

    public Recipe deleteRecipe(MyUserDetails userDetails, Integer recipeId) {
        Recipe recipe = recipeRepository.findByIdAndUserIdAndIsDeleted(recipeId, userDetails.getId(), Boolean.FALSE).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.Failed.RECIPE_NOT_FOUND));
        recipe.setIsDeleted(Boolean.TRUE);
        return recipeRepository.save(recipe);
    }
}
