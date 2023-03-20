package chess.domain.position.move;

public final class PassingMove implements PieceMove {

    @Override
    public boolean isMovable(boolean isPieceExist, boolean isLastIndex) {
        return true;
    }
}
