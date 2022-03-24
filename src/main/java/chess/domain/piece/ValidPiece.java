package chess.domain.piece;

import chess.domain.move.MoveStrategy;

public abstract class ValidPiece extends Piece {

    protected ValidPiece(Color color) {
        super(color);
    }

    public abstract MoveStrategy getMoveStrategy();

    @Override
    public boolean isBlank() {
        return false;
    }
}
