package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class RookMoveRule implements MoveRule {
    private static RookMoveRule instance;

    private RookMoveRule() {
    }

    public static RookMoveRule getInstance() {
        if (instance == null) {
            instance = new RookMoveRule();
        }
        return instance;
    }

    @Override
    public void validateMovement(Position currentPosition, Position nextPosition) {
        validateMoveToEmpty(nextPosition);
        validateStraight(currentPosition, nextPosition);
    }

    private void validateStraight(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isStraightEqual(nextPosition)) {
            throw new IllegalArgumentException("룩은 직선상으로만 움직일 수 있습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }

}
