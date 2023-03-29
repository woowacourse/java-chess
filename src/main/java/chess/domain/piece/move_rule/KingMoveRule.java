package chess.domain.piece.move_rule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;

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
    public List<Position> move(Position currentPosition, Position nextPosition) {
        validateNear(currentPosition, nextPosition);
        return Collections.emptyList();
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }

    @Override
    public boolean isPawnMove() {
        return false;
    }

    private void validateNear(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isNear(nextPosition)) {
            throw new IllegalArgumentException("킹은 인접한 칸으로만 이동할 수 있습니다.");
        }
    }
}
