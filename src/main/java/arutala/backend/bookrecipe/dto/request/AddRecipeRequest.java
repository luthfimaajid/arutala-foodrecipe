package arutala.backend.bookrecipe.dto.request;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddRecipeRequest {
    @ToString.Exclude
    private MultipartFile image;
    private String recipe_name;
    private String image_filename;
    private String how_to_cook;
    private Integer time_cook;
    private String ingredient;
    private Integer category_id;
    private Integer level_id;
}
