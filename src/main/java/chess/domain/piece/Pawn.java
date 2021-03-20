package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Pawn extends Piece {
    private static final String PAWN_INITIAL = "P";
    private static final int PAWN_SCORE = 1;
    private static final int BLACK_DIRECTION = 1;
    private static final int WHITE_DIRECTION = -1;
    private static final int DOUBLE_FORWARD = 2;
    private static final int PAWN_ROUTE_COUNT_ONE_FORWARD = 0;
    private static final double PAWN_ONE_SCORE = 1;
    private static final double PAWN_MULTIPLE_SCORE = 0.5;
    private static final int PAWN_COUNT_STANDARD = 1;

    public Pawn(Side side) {
        super(side, PAWN_INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        if (isSideEqualTo(Side.BLACK)) {
            return movableOneOrTwoSquare(rowDifference, columnDifference, BLACK_DIRECTION);
        }
        if (isSideEqualTo(Side.WHITE)) {
            return movableOneOrTwoSquare(rowDifference, columnDifference, WHITE_DIRECTION);

        }

        return false;
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Position.route(from, to);
    }

    @Override
    public boolean isBlank() {
        return false;
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
        if (oneSquareForward(rowDifference, columnDifference, direction)) {
            return true;
        }
        return twoSquareForward(rowDifference, direction);
    }

    private boolean twoSquareForward(int rowDifference, int direction) {
        return rowDifference == direction * DOUBLE_FORWARD && isInitPosition();
    }

    private boolean oneSquareForward(int rowDifference, int columnDifference, int direction) {
        return rowDifference == direction && Math.abs(columnDifference) < DOUBLE_FORWARD;
    }

    @Override
    public double score() {
        return PAWN_SCORE;
    }

    @Override
    public boolean diagonal(Position from, Position to) {
        return Math.abs(Position.differenceOfRow(from, to)) == Math.abs(Position.differenceOfColumn(from, to));
    }

    @Override
    public boolean forward(Position from, Position to) {
        return Position.differenceOfColumn(from, to) == PAWN_ROUTE_COUNT_ONE_FORWARD || Position.differenceOfRow(from, to) == PAWN_ROUTE_COUNT_ONE_FORWARD;
    }

    public static double scoreByCount(int count) {
        double score = 0;
        if (count == PAWN_COUNT_STANDARD) {
            score += PAWN_ONE_SCORE;
        }
        if (count > PAWN_COUNT_STANDARD) {
            score += count * PAWN_MULTIPLE_SCORE;
        }
        return score;
    }
}
