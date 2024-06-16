package arutala.backend.bookrecipe.dto;

import arutala.backend.bookrecipe.model.Recipe;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeDto {
    @JsonProperty("recipe_id")
    private Integer recipeId;

    @JsonProperty("recipe_name")
    private String recipeName;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("time_cook")
    private Integer timeCook;

    private String ingredient;

    @JsonProperty("how_to_cook")
    private String howToCook;

    @JsonProperty("is_favorite")
    private Boolean isFavorite;

    private CategoryDto categories;

    private LevelDto levels;

    private static RecipeDto createFromModel(Recipe recipe) {
        CategoryDto categoryDto = CategoryDto.createFromModel(recipe.getCategory());
        LevelDto levelDto = LevelDto.createFromModel(recipe.getLevel());
        Boolean isFavorite = Boolean.FALSE;
        if (recipe.getFavoriteFood() != null) {
            isFavorite = recipe.getFavoriteFood().getIsFavorite();
        }

        return RecipeDto.builder()
                .recipeId(recipe.getId())
                .recipeName(recipe.getRecipeName())
                .imageUrl(recipe.getImageFilename())
                .timeCook(recipe.getTimeCook())
                .ingredient(recipe.getIngredient())
                .howToCook(recipe.getHowToCook())
                .isFavorite(isFavorite)
                .categories(categoryDto)
                .levels(levelDto)
                .build();
    }

    public static RecipeDto createGetRecipesDto(Recipe recipe) {
        RecipeDto recipeDto = createFromModel(recipe);
        recipeDto.setHowToCook(null);
        recipeDto.setIngredient(null);

        return recipeDto;
    }

    public static RecipeDto createGetRecipeDetailDto(Recipe recipe) {
        return createFromModel(recipe);
    }
}