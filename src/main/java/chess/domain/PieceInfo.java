package chess.domain;

public class PieceInfo {
    private final Position position;
    private final Team team;

    public PieceInfo(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }
}
