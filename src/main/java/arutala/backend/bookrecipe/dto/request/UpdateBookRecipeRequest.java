package arutala.backend.bookrecipe.dto.request;

import arutala.backend.bookrecipe.dto.BookRecipeDto;
import arutala.backend.bookrecipe.dto.CategoryDto;
import arutala.backend.bookrecipe.dto.LevelDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateBookRecipeRequest extends BookRecipeDto {
    private CategoryDto categories;
    private LevelDto levels;
}
