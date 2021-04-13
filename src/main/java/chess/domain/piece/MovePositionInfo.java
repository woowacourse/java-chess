package chess.domain.piece;

public class MovePositionInfo {

    private Position source;
    private Position target;
    private String roomID;

    public MovePositionInfo() {
    }

    public MovePositionInfo(String source, String target, String roomID) {
        this.source = new Position(source);
        this.target = new Position(target);
        this.roomID = roomID;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }

    public String getRoomID() {
        return roomID;
    }

}
