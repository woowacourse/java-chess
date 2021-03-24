package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMethodCallException;
import java.util.Collections;
import java.util.List;

public final class Knight extends GamePiece {

    private static final String INITIAL = "N";
    private static final double SCORE = 2.5;
    private static final int MOVE_RULE_FIRST = 1;
    private static final int MOVE_RULE_SECOND = 2;

    public Knight(Side side) {
        super(side, INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        if (Math.abs(rowDifference) == MOVE_RULE_SECOND
                && Math.abs(columnDifference) == MOVE_RULE_FIRST) {
            return true;
        }
        return Math.abs(rowDifference) == MOVE_RULE_FIRST
                && Math.abs(columnDifference) == MOVE_RULE_SECOND;
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Collections.emptyList();
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
        return SCORE;
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
