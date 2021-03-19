package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;
import java.util.List;

public class QueenMovingStrategy implements MovingStrategy {

    private static final int LENGTH = 7;

    private final List<MoveVector> queensMoveVector = MoveVector.everyVectors();

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        int x = destination.XDifference(source);
        int y = destination.YDifference(source);

        return queensMoveVector.stream()
            .filter(moveVector -> moveVector.isSameDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int movingLength() {
        return LENGTH;
    }
}
