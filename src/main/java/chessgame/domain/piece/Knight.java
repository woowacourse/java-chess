package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Knight implements Piece {

    private final PieceType pieceType;
    private static final int STRAIGHT = 2;
    private static final int DIAGONAL = 1;

    private final Team team;

    private Knight(Team team) {
        this.pieceType = PieceType.KNIGHT;
        this.team = team;
    }

    public static Knight from(Team team) {
        return new Knight(team);
    }

    @Override
    public boolean isMovable(Point source, Point target, boolean hasTarget) {
        return isKnightMove(source, target);
    }

    @Override
    public Team team() {
        return team;
    }

    @Override
    public boolean isPiece(PieceType piece) {
        return pieceType.equals(piece);
    }

    @Override
    public double getScore() {
        return pieceType.getScore();
    }

    private boolean isKnightMove(Point source, Point target) {
        int fileDistance = Math.abs(source.fileDistance(target));
        int rankDistance = Math.abs(source.rankDistance(target));

        if (fileDistance == STRAIGHT && rankDistance == DIAGONAL) {
            return true;
        }
        return fileDistance == DIAGONAL && rankDistance == STRAIGHT;
    }

    @Override
    public String toString() {
        return pieceType.getName();
    }
}
