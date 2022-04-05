package chess.controller.dto;

public class ErrorResponse {

    private final StatusResponse status = StatusResponse.ERROR;
    private final String message;

    private ErrorResponse(String message) {
        this.message = message;
    }

    public static ErrorResponse from(RuntimeException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
