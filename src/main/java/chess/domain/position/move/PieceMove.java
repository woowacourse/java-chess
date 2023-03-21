package chess.domain.position.move;

public interface PieceMove {

    boolean isMovable(boolean isEmpty, boolean isLastPiece);
}
