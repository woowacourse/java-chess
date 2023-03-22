package chess.domain.piece.move;

public final class PawnCatchMove implements PieceMove {

    @Override
    public boolean isMovable(boolean isPieceExist, boolean isLastPiece) {
        if (isLastPiece && !isPieceExist) {
            return false;
        }

        return isPieceExist;
    }
}
