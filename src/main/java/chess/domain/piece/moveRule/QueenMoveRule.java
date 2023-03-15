package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

public class QueenMoveRule implements MoveRule {
    @Override
    public List<Position> possibleRoute(Position currentPosition, Position nextPosition) {
        if (!(currentPosition.isDiagonalEqual(nextPosition) || currentPosition.isStraightEqual(nextPosition))) {
            throw new IllegalArgumentException("퀸은 대각선 또는 직선 상으로만 움직일 수 있습니다.");
        }
        return currentPosition.getRoute(nextPosition);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.QUEEN;
    }
}
