package chess.domain.piece;

public class MovePositionInfo {

    private Position source;
    private Position target;

    public MovePositionInfo() {
    }

    public MovePositionInfo(String source, String target) {
        this.source = new Position(source);
        this.target = new Position(target);
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
