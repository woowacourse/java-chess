package chess.piece.movingstrategy;

import chess.board.Point;
import chess.piece.Vector;

public class EmptyMovingStrategy implements MovingStrategy {

    @Override
    public Vector findMovableVector(Point source, Point destination) {
        return null;
    }

    @Override
    public int getMoveLength() {
        return 0;
    }
}
