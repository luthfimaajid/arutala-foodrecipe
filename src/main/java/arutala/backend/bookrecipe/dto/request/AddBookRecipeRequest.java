package arutala.backend.bookrecipe.dto.request;

import arutala.backend.bookrecipe.dto.BookRecipeDto;
import arutala.backend.bookrecipe.dto.CategoryDto;
import arutala.backend.bookrecipe.dto.LevelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRecipeRequest extends BookRecipeDto {
    @JsonProperty("user_id")
    private Integer userId;
}
