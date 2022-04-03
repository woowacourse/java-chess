package chess.dto;

public class MoveRequestDto {

    private final String source;
    private final String target;

    public MoveRequestDto(final String source, final String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
