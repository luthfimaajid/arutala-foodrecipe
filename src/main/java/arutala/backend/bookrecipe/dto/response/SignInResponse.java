package arutala.backend.bookrecipe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private Integer id;
    private String token;
    private String type;
    private String username;
    private String role;
}
