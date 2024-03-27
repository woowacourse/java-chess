package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private static final int STAY = 0;
    private static final int ONE_SQUARE = 1;

    protected Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract Type identifyType();

    public abstract boolean canMove(Position source, Position target, Piece piece);

    public abstract List<Position> searchPath(Position source, Position target);

    public final boolean isBlack() {
        return color == Color.BLACK;
    }

    public final boolean isSameColor(Color color) {
        return this.color == color;
    }

    public final boolean isHorizontalMove(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        return Math.abs(rankDiff) != 0 && Math.abs(fileDiff) == 0;
    }

    public final boolean isVerticalMove(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        return Math.abs(rankDiff) == 0 && Math.abs(fileDiff) != 0;
    }

    public final boolean isDiagonalMove(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        return Math.abs(rankDiff) == Math.abs(fileDiff);
    }

    public final List<Position> slidingMove(Position source, Position target, boolean isVertical) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        int rankUnit = calculateUnit(rankDiff);
        int fileUnit = calculateUnit(fileDiff);
        int movingDistance = Math.abs(rankDiff);

        List<Position> path = new ArrayList<>();

        if (isVertical) {
            movingDistance = Math.abs(fileDiff);
        }

        for (int i = movingDistance; i != ONE_SQUARE; i--) {
            source = source.move(fileUnit, rankUnit);
            path.add(source);
        }
        return path;
    }

    private int calculateUnit(int difference) {
        if (difference == STAY) {
            return STAY;
        }
        return difference / Math.abs(difference);
    }
}
