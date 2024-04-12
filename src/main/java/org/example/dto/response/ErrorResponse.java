package org.example.dto.response;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    @JsonProperty("code")
    private int code;

    @JsonProperty("type")
    private String type;

    @JsonProperty("message")
    private String message;
}
