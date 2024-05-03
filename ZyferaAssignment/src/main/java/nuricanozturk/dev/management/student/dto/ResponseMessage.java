package nuricanozturk.dev.management.student.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseMessage<T>(
        String message,
        @JsonProperty("status_code")
        int statusCode,
        T data)
{
}
