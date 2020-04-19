package chess.dto;

public class MoveResultDTO {
    private final String message;
    private final String style;

    public MoveResultDTO(String message, String style) {
        this.message = message;
        this.style = style;
    }

    public String getMessage() {
        return message;
    }

    public String getStyle() {
        return this.style;
    }
}
