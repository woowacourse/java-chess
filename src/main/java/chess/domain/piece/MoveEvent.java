package chess.domain.piece;

public class MoveEvent {
    private Piece source;
    private Piece target;
    private Path path;

    public MoveEvent(Piece source, Piece target, Path path) {
        this.source = source;
        this.target = target;
        this.path = path;
    }

    public Piece getSource() {
        return source;
    }

    public Position getSourcePosition() {
        return source.getPosition();
    }

    public Piece getTarget() {
        return target;
    }

    public Position getTargetPosition() {
        return target.getPosition();
    }

    public Path getPath() {
        return path;
    }
}
