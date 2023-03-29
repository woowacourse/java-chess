package chess.domain.piece.move_rule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;

public class KnightMoveRule implements MoveRule {

    private static KnightMoveRule instance;

    private KnightMoveRule() {
    }

    public static KnightMoveRule getInstance() {
        if (instance == null) {
            instance = new KnightMoveRule();
        }
        return instance;
    }

    @Override
    public List<Position> move(Position currentPosition, Position nextPosition) {
        validateKnightDirection(currentPosition, nextPosition);
        return Collections.emptyList();
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public boolean isPawnMove() {
        return false;
    }

    private void validateKnightDirection(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isKnightPosition(nextPosition)) {
            throw new IllegalArgumentException("나이트는 L 모양으로만 움직일 수 있습니다.");
        }
    }
}
