package chess.domain.piece;

import chess.domain.Camp;

public abstract class NotNullPiece extends Piece {

    protected NotNullPiece(final Camp camp, final PieceName pieceName) {
        super(camp, pieceName);
    }

    @Override
    public final boolean isNullPiece() {
        return false;
    }
}
