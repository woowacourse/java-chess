package chess.domain.movepatterns;

import chess.domain.Point;
import chess.domain.pieces.Direction;

import java.util.List;

public class QueenPattern implements MovePattern {

    @Override
    public boolean canMove(Point source, Point target) {
        List<Direction> queenDirections = Direction.everyDirection();
        return queenDirections.contains(Direction.of(source, target));
    }
}
