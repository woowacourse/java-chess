package chess.domain.pieceRule;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Optional;

public interface PieceRule {
    Optional<MoveDirection> canMoveDirection(Position sourcePosition, Position targetPosition);

    boolean canMoveRange(Position sourcePosition, Position targetPosition);
}
