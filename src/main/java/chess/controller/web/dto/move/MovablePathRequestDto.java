package chess.controller.web.dto.move;

public class MovablePathRequestDto {

    private final String source;

    public MovablePathRequestDto(final String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
