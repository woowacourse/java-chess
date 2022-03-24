package chess.domain.piece;

import chess.domain.move.KnightMoveStrategy;
import chess.domain.move.MoveStrategy;

public class Knight extends ValidPiece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new KnightMoveStrategy();
    }
}
