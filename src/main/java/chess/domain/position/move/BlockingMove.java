package chess.domain.position.move;

public final class BlockingMove implements PieceMove {

    @Override
    public boolean isMovable(boolean isPieceExist, boolean isLastPiece) {
        if (isLastPiece) {
            return true;
        }

        return !isPieceExist;
    }
}
