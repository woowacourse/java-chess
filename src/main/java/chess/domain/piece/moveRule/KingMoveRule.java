package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;

public class KingMoveRule implements MoveRule{
    @Override
    public List<Position> possibleRoute(Position currentPosition, Position nextPosition) {
        if(!currentPosition.isNear(nextPosition)){
            throw new IllegalArgumentException("킹은 인접한 칸으로만 이동할 수 있습니다.");
        }
        return Collections.emptyList();
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }
}
