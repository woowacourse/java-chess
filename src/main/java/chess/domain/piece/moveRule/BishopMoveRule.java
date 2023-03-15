package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

public class BishopMoveRule implements MoveRule {

    @Override
    public List<Position> possibleRoute(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isDiagonalEqual(nextPosition)) {
            throw new IllegalArgumentException("비숍은 대각선상으로만 움직일 수 있습니다.");
        }
        return currentPosition.getDiagonalRoute(nextPosition);
    }

    public PieceType pieceType() {
        return PieceType.BISHOP;
    }
}
