package chess.controller.dto;

public class MoveResultDto {
    private final String message;
    private final String style;

    public MoveResultDto(String message, String style) {
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
