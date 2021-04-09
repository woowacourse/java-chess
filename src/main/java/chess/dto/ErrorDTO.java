package chess.dto;

public class ErrorDTO {
    private final String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
