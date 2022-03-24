package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import chess.domain.move.PawnMoveStrategy;

public class Pawn extends ValidPiece {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new PawnMoveStrategy();
    }
}
