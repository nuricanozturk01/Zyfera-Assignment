package nuricanozturk.dev.management.student.dto;

import jakarta.validation.constraints.*;

public record GradeCreateDTO(
        @NotNull(message = "code Number cannot be null")
        @NotEmpty(message = "code Number cannot be empty")
        @Size(min = 3, message = "code Number must be 3 characters long")
        @Size(max = 11, message = "code Number must be 11 characters long")
        String code,

        @Min(value = 0, message = "value must be greater than 0")
        @Max(value = 100, message = "value must be less than 100")
        double value
)
{
}
