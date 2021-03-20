package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Pawn extends Piece {
    private static final String PAWN_INITIAL = "P";
    private static final int PAWN_SCORE = 1;

    public Pawn(Side side) {
        super(side, PAWN_INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        if (isSideEqualTo(Side.BLACK)) {
            return movableOneOrTwoSquare(rowDifference, columnDifference, 1);
        }
        if (isSideEqualTo(Side.WHITE)) {
            return movableOneOrTwoSquare(rowDifference, columnDifference, -1);
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
        return rowDifference == direction * 2 && isInitPosition();
    }

    private boolean oneSquareForward(int rowDifference, int columnDifference, int direction) {
        return rowDifference == direction && Math.abs(columnDifference) < 2;
    }

    @Override
    public double score() {
        return PAWN_SCORE;
    }
}
