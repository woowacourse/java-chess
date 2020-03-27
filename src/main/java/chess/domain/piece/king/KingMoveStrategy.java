package chess.domain.piece.king;

import chess.domain.piece.MoveStrategy;
import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;

public class KingMoveStrategy implements MoveStrategy {
    @Override
    public boolean isNotMovableTo(Position start, Position destination) {
        return !MovableAreaFactory.kingOf(start).contains(destination);
    }
}
