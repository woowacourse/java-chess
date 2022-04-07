package chess.dto.response;

public class ErrorResponse {
    private String message;

    public ErrorResponse(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
