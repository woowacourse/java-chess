package chess.dto;

public class MovableRequestDto {
    private String from;

    public MovableRequestDto(final String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }
}
