package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;

public class EmptyMovingStrategy implements MovingStrategy {

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        return null;
    }

    @Override
    public int movingLength() {
        return 0;
    }
}
