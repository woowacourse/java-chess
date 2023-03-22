package chess.domain.piece.move_rule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

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
    public List<Position> move(Position currentPosition, Position nextPosition) {
        validateDiagonal(currentPosition, nextPosition);
        return currentPosition.getRoute(nextPosition);
    }

    public PieceType pieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public boolean isPawnMove() {
        return false;
    }

    private void validateDiagonal(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isDiagonalEqual(nextPosition)) {
            throw new IllegalArgumentException("비숍은 대각선상으로만 움직일 수 있습니다.");
        }
    }
}
