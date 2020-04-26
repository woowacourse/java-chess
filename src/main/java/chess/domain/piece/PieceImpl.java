package chess.domain.piece;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;

public class PieceImpl {
    private final String name;
    private final CanNotMoveStrategy canNotMoveStrategy;


    public PieceImpl(String name, CanNotMoveStrategy canNotMoveStrategy) {
        this.name = name;
        this.canNotMoveStrategy = canNotMoveStrategy;
    }

    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return canNotMoveStrategy.canNotMove(from, to, piecesState);
    }
}
