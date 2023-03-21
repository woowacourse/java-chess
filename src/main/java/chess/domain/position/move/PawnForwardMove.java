package chess.domain.position.move;

public final class PawnForwardMove implements PieceMove {

    @Override
    public boolean isMovable(boolean isEmpty, boolean isLastPiece) {
        if (isLastPiece && !isEmpty) {
            return false;
        }

        return isEmpty;
    }
}
