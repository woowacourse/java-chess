package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.QueenMoveStrategy;

import java.util.List;

public class Queen extends Piece {
    private final String symbol;

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
}
