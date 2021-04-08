package chess.domain.piece;

public class MovePositionInfo {

    private String source;
    private String target;

    public MovePositionInfo() {
    }

    public MovePositionInfo(String source, String target) {
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
