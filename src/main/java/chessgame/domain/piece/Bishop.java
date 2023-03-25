package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Bishop implements Piece {
    private final PieceType pieceType;
    private final Team team;

    private Bishop(Team team) {
        this.pieceType = PieceType.BISHOP;
        this.team = team;
    }

    public static Bishop from(Team team) {
        return new Bishop(team);
    }

    @Override
    public boolean isMovable(Point source, Point target, boolean hasTarget) {
        return source.isDiagonal(target);
    }

    @Override
    public Team team() {
        return team;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double getScore() {
        return pieceType.getScore();
    }

    @Override
    public String toString() {
        return pieceType.getName();
    }
}
