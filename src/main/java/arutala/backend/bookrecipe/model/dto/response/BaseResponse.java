package arutala.backend.bookrecipe.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private String message;
    private int statusCode;
    private String status;
}
