package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public interface MoveRule {
    void validateMovement(Position currentPosition, Position nextPosition);

    PieceType pieceType();

    default void validateMoveToEmpty(Position nextPosition) {
        if (nextPosition == null) {
            throw new IllegalArgumentException("기물이 이동할 수 없는 위치입니다.");
        }
    }
}
