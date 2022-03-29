package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.KingMoveStrategy;
import chess.domain.piece.strategy.KnightMoveStrategy;

import java.util.List;

public class Knight extends Piece {

    private final String symbol;
    private static final double SCORE = 2.5;

    public Knight(final Team team, final String symbol) {
        super(team, new KnightMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public List<Direction> possibleDirections() {
        return Direction.getKnightDirection();
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
