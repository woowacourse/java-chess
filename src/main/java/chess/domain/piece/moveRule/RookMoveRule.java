package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

public class RookMoveRule implements MoveRule {
    @Override
    public List<Position> possibleRoute(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isStraightEqual(nextPosition)) {
            throw new IllegalArgumentException("룩은 직선상으로만 움직일 수 있습니다.");
        }
        return currentPosition.getRoute(nextPosition);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }

}
