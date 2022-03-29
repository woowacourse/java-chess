package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.QueenMoveStrategy;

import java.util.List;

public final class Queen extends Piece {

    private final String symbol;
    private static final double SCORE = 9.0;

    public Queen(final Team team, final String symbol) {
        super(team, new QueenMoveStrategy());
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
