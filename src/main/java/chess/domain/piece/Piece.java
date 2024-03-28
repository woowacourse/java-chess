package chess.domain.piece;

import chess.domain.point.Point;

public abstract class Piece {

    private final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public static Piece bishopFrom(Team team) {
        return Bishop.from(team);
    }

    public static Piece kingFrom(Team team) {
        return King.from(team);
    }

    public static Piece knightFrom(Team team) {
        return Knight.from(team);
    }

    public static Piece pawnFrom(Team team) {
        return Pawn.from(team);
    }

    public static Piece queenFrom(Team team) {
        return Queen.from(team);
    }

    public static Piece rookFrom(Team team) {
        return Rook.from(team);
    }

    public static Piece empty() {
        return Empty.getEmpty();
    }

    public abstract boolean isMovable(Point currentPoint, Point nextPoint, Piece target);

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isSameTeam(Piece piece) {
        return this.team == piece.team;
    }

    public boolean isWhite() {
        return team.isWhite();
    }
}
