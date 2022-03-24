package chess.domain.piece;

import chess.domain.move.KingMoveStrategy;
import chess.domain.move.MoveStrategy;

public class King extends ValidPiece {

    public King(final Color color) {
        super(color);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new KingMoveStrategy();
    }
}
