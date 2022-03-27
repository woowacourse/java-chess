package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.PawnMoveStrategy;

import java.util.List;

public class Pawn extends Piece{
    private final String symbol;

    public Pawn(final Team team, final String symbol) {
        super(team, new PawnMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public List<Direction> possibleDirections() {
        if (team().equals(Team.WHITE)) {
            return Direction.getWhitePawnDirection();
        }

       return Direction.getBlackPawnDirection();
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
