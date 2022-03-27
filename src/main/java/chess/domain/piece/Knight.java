package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.KingMoveStrategy;

import java.util.List;

public class Knight extends Piece {
    private final String symbol;

    public Knight(final Team team, final String symbol) {
        super(team, new KingMoveStrategy());
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
}
