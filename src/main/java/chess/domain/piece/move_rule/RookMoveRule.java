package chess.domain.piece.move_rule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

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
    public List<Position> move(Position currentPosition, Position nextPosition) {
        validateStraight(currentPosition, nextPosition);
        return currentPosition.getRoute(nextPosition);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }

    @Override
    public boolean isPawnMove() {
        return false;
    }

    @Override
    public boolean isKingMove() {
        return false;
    }

    private void validateStraight(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isStraightEqual(nextPosition)) {
            throw new IllegalArgumentException("룩은 직선상으로만 움직일 수 있습니다.");
        }
    }
}
