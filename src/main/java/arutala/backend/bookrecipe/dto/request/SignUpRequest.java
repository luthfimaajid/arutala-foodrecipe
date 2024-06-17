package arutala.backend.bookrecipe.dto.request;

import arutala.backend.bookrecipe.util.ResponseMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {
    @NotNull(message = ResponseMessage.Failed.EMPTY_COLUMN)
    @Pattern(regexp = "^[a-zA-Z0-9]{1,100}$", message = ResponseMessage.Failed.USERNAME_MALFORMED)
    private String username;

    @NotNull(message = ResponseMessage.Failed.EMPTY_COLUMN)
    @Pattern(regexp = "^[a-zA-Z]{1,255}$", message = ResponseMessage.Failed.CONTAIN_SPECIAL_CHARACTER)
    private String fullname;

    @NotNull(message = ResponseMessage.Failed.EMPTY_COLUMN)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,50}$", message = ResponseMessage.Failed.PASSWORD_MINIUM_SPECIFICATION)
    private String password;

    @NotNull(message = ResponseMessage.Failed.EMPTY_COLUMN)
    private String retypePassword;
}