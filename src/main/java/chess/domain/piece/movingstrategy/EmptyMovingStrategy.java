package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;

public class EmptyMovingStrategy implements MovingStrategy {

    @Override
    public MoveVector findMovableVector(Point source, Point destination) {
        return null;
    }

    @Override
    public int getMoveLength() {
        return 0;
    }
}
