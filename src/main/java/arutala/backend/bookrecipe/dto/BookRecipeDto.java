package arutala.backend.bookrecipe.dto;

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
public class BookRecipeDto {
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
}