package quiz.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class RegisterRequest {
    @Pattern(regexp = "^[A-Za-z\\d]{4,20}$", message = "{username.pattern}")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "{password.pattern}")
    private String password;
}