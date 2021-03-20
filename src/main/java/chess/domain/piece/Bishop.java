package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMethodCallException;

import java.util.List;

public class Bishop extends Piece {
    private static final String BISHOP_INITIAL = "B";
    private static final int BISHOP_SCORE = 3;

    public Bishop(Side side) {
        super(side, BISHOP_INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        return isDiagonal(rowDifference, columnDifference);
    }

    private boolean isDiagonal(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) == Math.abs(columnDifference);
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
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
    }

    @Override
    public boolean diagonal(Position from, Position to) {
        throw new InvalidMethodCallException();
    }

    @Override
    public boolean forward(Position from, Position to) {
        throw new InvalidMethodCallException();
    }
}
