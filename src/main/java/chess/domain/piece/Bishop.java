package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.BishopMoveStrategy;

import java.util.List;

public final class Bishop extends Piece {

    private final String symbol;
    private static final double SCORE = 3.0;

    public Bishop(final Team team, final String symbol) {
        super(team, new BishopMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public List<Direction> possibleDirections() {
        return Direction.getBishopDirection();
    }

    @Override
    public String symbol() {
        return symbol;
    }

    @Override
    public double score() {
        return SCORE;
    }
}
