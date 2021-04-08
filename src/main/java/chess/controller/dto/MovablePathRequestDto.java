package chess.controller.dto;

public class MovablePathRequestDto {

    private final String source;

    public MovablePathRequestDto(final String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
