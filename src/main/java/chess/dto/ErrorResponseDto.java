package chess.dto;

import chess.StatusCode;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ErrorResponseDto {

    @SerializedName("error")
    private final String errorMessage;
    private final StatusCode statusCode;

    private ErrorResponseDto(final String errorMessage, final StatusCode statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public static ErrorResponseDto of(final Exception e, final StatusCode statusCode) {
        return new ErrorResponseDto(e.getMessage(), statusCode);
    }

    public static StatusCode toStatusCode(final String json) {
        final Gson gson = new Gson();
        final ErrorResponseDto dto = gson.fromJson(json, ErrorResponseDto.class);
        return dto.statusCode;
    }
}
