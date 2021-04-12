package chess.domain.state;

import chess.domain.piece.King;
import chess.domain.piece.Piece;

public abstract class Playing implements GameState {
    @Override
    public final boolean isFinished() {
        return false;
    }

    final void validateSourcePieceIsEmpty(final Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("움직이려는 대상이 빈칸입니다");
        }
    }

    final boolean isKingCaught(Piece targetPiece) {
        return targetPiece instanceof King;
    }
}
