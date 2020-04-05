package chess.controller.dto;

public class StatusDto {
    private final String message;

    public StatusDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
