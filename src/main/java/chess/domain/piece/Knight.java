package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMethodCallException;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
    private static final String KNIGHT_INITIAL = "N";
    private static final double KNIGHT_SCORE = 2.5;
    private static final int KNIGHT_MOVE_RULE_FIRST = 1;
    private static final int KNIGHT_MOVE_RULE_SECOND = 2;

    public Knight(Side side) {
        super(side, KNIGHT_INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        if (Math.abs(rowDifference) == KNIGHT_MOVE_RULE_SECOND && Math.abs(columnDifference) == KNIGHT_MOVE_RULE_FIRST) {
            return true;
        }
        return Math.abs(rowDifference) == KNIGHT_MOVE_RULE_FIRST && Math.abs(columnDifference) == KNIGHT_MOVE_RULE_SECOND;
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Collections.emptyList();
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
        return KNIGHT_SCORE;
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
