package chess.domain.position;

public class MovePath {
    private final Position source;
    private final Position target;

    public MovePath(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
