package chess.domain.piece.move;

public interface PieceMove {

    boolean isMovable(boolean isPieceExist, boolean isLastIndex);
}
