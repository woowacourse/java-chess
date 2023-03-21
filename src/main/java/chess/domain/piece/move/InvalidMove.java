package chess.domain.piece.move;

public final class InvalidMove implements PieceMove {

    @Override
    public boolean isMovable(boolean isPieceExist, boolean isLastIndex) {
        return false;
    }
}
