package chess.domain.piece.move.pawn;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;
import chess.domain.piece.move.MovingStrategy;

public class PawnMovingStrategy implements MovingStrategy {

    private final PawnSupport support;

    public PawnMovingStrategy(Color color) {
        this.support = new PawnSupport(color);
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        PawnMoveForwardChain chain = new PawnMoveForwardChain(support);
        return chain.move(route, emptyPoints);
    }
}
