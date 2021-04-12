package chess.controller.web.dto;

public class ErrorDto {
    private final String message;

    public ErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
