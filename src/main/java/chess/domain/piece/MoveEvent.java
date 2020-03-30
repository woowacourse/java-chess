package chess.domain.piece;

public class MoveEvent {
    private Position source;
    private Position target;
    private Path path;

    public MoveEvent(Position source, Position target, Path path) {
        this.source = source;
        this.target = target;
        this.path = path;
    }

    public Position getSourcePosition() {
        return source;
    }

    public Position getTargetPosition() {
        return target;
    }

    public Path getPath() {
        return path;
    }
}
