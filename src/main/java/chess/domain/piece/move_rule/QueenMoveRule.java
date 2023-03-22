package chess.domain.piece.move_rule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

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
    public List<Position> move(Position currentPosition, Position nextPosition) {
        validateStraightOrDiagonal(currentPosition, nextPosition);
        return currentPosition.getRoute(nextPosition);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.QUEEN;
    }

    @Override
    public boolean isPawnMove() {
        return false;
    }

    private void validateStraightOrDiagonal(Position currentPosition, Position nextPosition) {
        if (!(currentPosition.isDiagonalEqual(nextPosition) || currentPosition.isStraightEqual(nextPosition))) {
            throw new IllegalArgumentException("퀸은 대각선 또는 직선 상으로만 움직일 수 있습니다.");
        }
    }
}
