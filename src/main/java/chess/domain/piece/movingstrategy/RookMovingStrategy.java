package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;
import java.util.List;

public class RookMovingStrategy implements MovingStrategy {

    static final int LENGTH = 7;

    private final List<MoveVector> rooksMoveVector = MoveVector.axisVectors();

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        int x = destination.XDifference(source);
        int y = destination.YDifference(source);

        return rooksMoveVector.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int movingLength() {
        return LENGTH;
    }
}
