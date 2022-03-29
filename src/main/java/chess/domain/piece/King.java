package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.KingMoveStrategy;

import java.util.List;

public class King extends Piece {

    private final String symbol;
    private static final double SCORE = 0.0;

    public King(final Team team, final String symbol) {
        super(team, new KingMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public List<Direction> possibleDirections() {
        return Direction.getAllDirection();
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
