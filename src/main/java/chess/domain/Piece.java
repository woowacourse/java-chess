package chess.domain;

import java.util.List;

public abstract class Piece {
    protected final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    abstract List<Point> getCandidatePoints(Point start, Point end);

    boolean isSameTeam(Piece endPiece) {
        return this.team == endPiece.team;
    }
}
