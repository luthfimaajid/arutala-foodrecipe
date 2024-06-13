package arutala.backend.bookrecipe.dto.response;

import arutala.backend.bookrecipe.dto.BookRecipeDto;
import arutala.backend.bookrecipe.dto.CategoryDto;
import arutala.backend.bookrecipe.dto.LevelDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBookRecipesResponse extends BookRecipeDto {
    private CategoryDto categories;
    private LevelDto levels;
}
