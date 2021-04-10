package chess.controller.dto;

public class ErrorDto {
    private final String message;

    public ErrorDto(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
