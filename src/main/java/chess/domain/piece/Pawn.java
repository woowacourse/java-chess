package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import java.util.List;

public final class Pawn extends GamePiece {

    private static final String INITIAL = "P";
    private static final int BLACK_DIRECTION = 1;
    private static final int WHITE_DIRECTION = -1;
    private static final int DOUBLE_FORWARD = 2;
    private static final double SINGLE_PAWN_SCORE = 1;
    private static final double MULTIPLE_PAWN_SCORE = 0.5;
    private static final int MULTIPLE_SCORE_LIMIT = 1;

    public Pawn(Side side) {
        super(side, INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        if (isSideEqualTo(Side.NONE)) {
            return false;
        }

        if (isSideEqualTo(Side.BLACK)) {
            return movableOneOrTwoSquare(rowDifference, columnDifference, BLACK_DIRECTION);
        }
        return movableOneOrTwoSquare(rowDifference, columnDifference, WHITE_DIRECTION);
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Position.route(from, to);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    private boolean movableOneOrTwoSquare(int rowDifference, int columnDifference, int direction) {
        if (oneSquareForwardOrDiagonal(rowDifference, columnDifference, direction)) {
            return true;
        }
        return twoSquareForward(rowDifference, columnDifference, direction);
    }

    private boolean twoSquareForward(int rowDifference, int columnDifference, int direction) {
        if (!isInitPosition()) {
            return false;
        }
        if (columnDifference != 0) {
            return false;
        }
        return rowDifference == direction * DOUBLE_FORWARD;
    }

    private boolean oneSquareForwardOrDiagonal(int rowDifference, int columnDifference,
            int direction) {
        return rowDifference == direction && Math.abs(columnDifference) < DOUBLE_FORWARD;
    }

    @Override
    public double score() {
        return SINGLE_PAWN_SCORE;
    }

    @Override
    public boolean diagonal(Position from, Position to) {
        int rowDifference = Math.abs(Position.differenceOfRow(from, to));
        int columnDifference = Math.abs(Position.differenceOfColumn(from, to));

        return rowDifference == 1 && columnDifference == 1;
    }

    @Override
    public boolean forward(Position from, Position to) {
        if (Position.differenceOfColumn(from, to) != 0) {
            return false;
        }
        return Math.abs(Position.differenceOfRow(from, to)) < 3;
    }

    public static double scoreByCount(int count) {
        double score = 0;
        if (count == MULTIPLE_SCORE_LIMIT) {
            score += SINGLE_PAWN_SCORE;
        }
        if (count > MULTIPLE_SCORE_LIMIT) {
            score += count * MULTIPLE_PAWN_SCORE;
        }
        return score;
    }
}
