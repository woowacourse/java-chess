package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Rook implements Piece {

    private final PieceType pieceType;

    private final Team team;

    private Rook(Team team) {
        this.pieceType = PieceType.ROOK;
        this.team = team;
    }

    public static Rook from(Team team) {
        return new Rook(team);
    }

    @Override
    public Team team() {
        return team;
    }

    @Override
    public boolean isMovable(Point source, Point target, boolean hasTarget) {
        return source.isHorizontal(target) || source.isVertical(target);
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
