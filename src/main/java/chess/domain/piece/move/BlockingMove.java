package chess.domain.piece.move;

public final class BlockingMove implements PieceMove {

    @Override
    public boolean isMovable(boolean isPieceExist, boolean isLastPiece) {
        if (isLastPiece) {
            return true;
        }

        return !isPieceExist;
    }
}
