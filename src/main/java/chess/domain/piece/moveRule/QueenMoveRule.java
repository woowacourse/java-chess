package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class QueenMoveRule implements MoveRule {

    private static QueenMoveRule instance;

    private QueenMoveRule() {
    }

    public static QueenMoveRule getInstance() {
        if (instance == null) {
            instance = new QueenMoveRule();
        }
        return instance;
    }

    @Override
    public void validateMovement(Position currentPosition, Position nextPosition) {
        validateMoveToEmpty(nextPosition);
        validateDiagonalOrStraight(currentPosition, nextPosition);
    }


    private void validateDiagonalOrStraight(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isStraightEqual(nextPosition) && !currentPosition.isDiagonalEqual(nextPosition)) {
            throw new IllegalArgumentException("퀸은 대각선상 또는 직선상으로만 움직일 수 있습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PieceType.QUEEN;
    }
}
