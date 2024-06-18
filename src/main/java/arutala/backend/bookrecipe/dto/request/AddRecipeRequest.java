package arutala.backend.bookrecipe.dto.request;

import arutala.backend.bookrecipe.util.ResponseMessage;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class AddRecipeRequest {
    @ToString.Exclude
    @NotNull(message = ResponseMessage.Failed.EMPTY_COLUMN)
    private MultipartFile image_filename;

    @NotBlank(message = ResponseMessage.Failed.EMPTY_COLUMN)
    @Pattern(regexp = "^[a-zA-Z]{1,255}$", message = ResponseMessage.Failed.CONTAIN_SPECIAL_CHARACTER)
    private String recipe_name;

    @NotBlank(message = ResponseMessage.Failed.EMPTY_COLUMN)
    private String how_to_cook;

    @Max(value = 999, message = ResponseMessage.Failed.TIME_COOK_INVALID)
    @Min(value = 1, message = ResponseMessage.Failed.TIME_COOK_INVALID)
    @NotNull(message = ResponseMessage.Failed.EMPTY_COLUMN)
    private Integer time_cook;

    @NotBlank(message = ResponseMessage.Failed.EMPTY_COLUMN)
    private String ingredient;

    @NotNull(message = ResponseMessage.Failed.EMPTY_COLUMN)
    private Integer category_id;

    @NotNull(message = ResponseMessage.Failed.EMPTY_COLUMN)
    private Integer level_id;
}
