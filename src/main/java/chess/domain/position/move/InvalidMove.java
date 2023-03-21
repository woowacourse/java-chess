package chess.domain.position.move;

public final class InvalidMove implements PieceMove {

    @Override
    public boolean isMovable(boolean isEmpty, boolean isLastPiece) {
        return false;
    }
}
