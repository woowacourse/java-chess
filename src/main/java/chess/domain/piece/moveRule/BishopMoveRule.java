package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class BishopMoveRule implements MoveRule {

    private static BishopMoveRule instance;

    private BishopMoveRule() {
    }

    public static BishopMoveRule getInstance() {
        if (instance == null) {
            instance = new BishopMoveRule();
        }
        return instance;
    }

    @Override
    public void validateMovement(Position currentPosition, Position nextPosition) {
        validateMoveToEmpty(nextPosition);
        validateDiagonal(currentPosition, nextPosition);
    }

    private void validateDiagonal(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isDiagonalEqual(nextPosition)) {
            throw new IllegalArgumentException("비숍은 대각선상으로만 움직일 수 있습니다.");
        }
    }

    public PieceType pieceType() {
        return PieceType.BISHOP;
    }
}
