package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.RookMoveStrategy;

import java.util.List;

public final class Rook extends Piece {

    private final String symbol;
    private static final double SCORE = 5.0;

    public Rook(final Team team, final String symbol) {
        super(team, new RookMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public List<Direction> possibleDirections() {
        return Direction.getRookDirection();
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
