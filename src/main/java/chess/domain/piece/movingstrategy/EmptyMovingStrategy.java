package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.Vector;

public class EmptyMovingStrategy implements MovingStrategy {

    @Override
    public Vector findMovableVector(Point source, Point destination) {
        throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
    }

    @Override
    public int getMoveLength() {
        return 0;
    }
}
