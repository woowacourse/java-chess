package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMethodCallException;
import java.util.Collections;
import java.util.List;

public final class King extends GamePiece {

    private static final String INITIAL = "K";
    private static final int SCORE = 0;

    public King(Side side) {
        super(side, INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) == 1 || Math.abs(columnDifference) == 1;
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
        return true;
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
