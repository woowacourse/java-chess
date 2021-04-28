package chess.dto.chess;

public class MoveRequestDto {

    private final String source;
    private final String target;

    public MoveRequestDto(final String source, final String target) {
        this.source = source;
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public String getSource() {
        return source;
    }
}
