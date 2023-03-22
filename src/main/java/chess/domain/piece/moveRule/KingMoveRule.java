package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class KingMoveRule implements MoveRule {

    private static KingMoveRule instance;

    private KingMoveRule() {
    }

    public static KingMoveRule getInstance() {
        if (instance == null) {
            instance = new KingMoveRule();
        }
        return instance;
    }

    @Override
    public void validateMovement(Position currentPosition, Position nextPosition) {
        validateMoveToEmpty(nextPosition);
        validateNear(currentPosition, nextPosition);
    }

    private void validateNear(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isNear(nextPosition)) {
            throw new IllegalArgumentException("킹은 인접한 칸으로만 이동할 수 있습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }
}
