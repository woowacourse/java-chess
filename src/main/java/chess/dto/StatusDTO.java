package chess.dto;

public class StatusDTO {
    private final String message;

    public StatusDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
