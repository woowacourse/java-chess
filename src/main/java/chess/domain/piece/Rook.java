package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.RookMoveStrategy;

import java.util.List;

public class Rook extends Piece {
    private final String symbol;

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
}
