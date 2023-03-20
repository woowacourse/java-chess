package chess.domain.position.move;

public final class PawnForwardMove implements PieceMove {

    @Override
    public boolean isMovable(boolean isPieceExist, boolean isLastPiece) {
        if (isLastPiece && isPieceExist) {
            return false;
        }

        return !isPieceExist;
    }
}
