package indiecode.api.siguard.security.Controllers.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message","jwtToken","status"})
public record AuthRespondeDto(String username,String message, String jwtToken, boolean status) {
}
