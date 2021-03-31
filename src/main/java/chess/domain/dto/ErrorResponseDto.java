package chess.domain.dto;

public class ErrorResponseDto {

    private final ResponseStatus status;
    private final String message;

    private ErrorResponseDto(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponseDto message(String message) {
        return new ErrorResponseDto(ResponseStatus.ERROR, message);
    }

    public String getStatus() {
        return status.getStatus();
    }

    public String getMessage() {
        return message;
    }
}
