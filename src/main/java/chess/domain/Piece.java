package chess.domain;

public class Piece {

    private final Point point;
    private final Team team;

    public Piece(Point point, Team team) {
        this.point = point;
        this.team = team;
    }
}
