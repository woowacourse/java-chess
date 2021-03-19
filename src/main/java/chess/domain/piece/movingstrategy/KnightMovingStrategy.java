package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;
import java.util.List;

public class KnightMovingStrategy implements MovingStrategy {

    private static final int LENGTH = 1;

    private final List<MoveVector> kingsMoveVector = MoveVector.knightVectors();

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        int x = destination.XDifference(source);
        int y = destination.YDifference(source);

        return kingsMoveVector.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int movingLength() {
        return LENGTH;
    }
}
