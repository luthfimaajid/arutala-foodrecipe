package arutala.backend.bookrecipe.dto;

import arutala.backend.bookrecipe.model.Category;
import arutala.backend.bookrecipe.model.Level;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LevelDto {
    @JsonProperty("level_id")
    private Integer levelId;

    @JsonProperty("level_name")
    private String levelName;

    public static LevelDto createFromModel(Level level) {
        return LevelDto.builder()
                .levelId(level.getId())
                .levelName(level.getLevelName())
                .build();
    }
}
