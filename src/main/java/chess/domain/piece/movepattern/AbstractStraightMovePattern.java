package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;

public abstract class AbstractStraightMovePattern implements MovePattern {

    @Override
    public boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    @Override
    public Direction findDirection(Position src, Position dest) {
        List<Direction> directions = getDirections();
        return directions.stream()
                .filter(direction -> src.canCrossMovingStraight(direction, dest))
                .findFirst()
                .orElse(null);
    }

    @Override
    public abstract List<Direction> getDirections();
}
