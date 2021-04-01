package chess.domain.position;

public class MovePositionDTO {
    private final String source;
    private final String target;

    public MovePositionDTO(String source, String target) {
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
