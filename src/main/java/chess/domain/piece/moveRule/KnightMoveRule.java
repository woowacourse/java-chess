package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;

public class KnightMoveRule implements MoveRule {

    @Override
    public List<Position> possibleRoute(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isKnightPosition(nextPosition)) {
            throw new IllegalArgumentException("나이트는 L 모양으로만 움직일 수 있습니다.");
        }
        return Collections.emptyList();
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KNIGHT;
    }
}
