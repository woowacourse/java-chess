package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;

public abstract class AbstractStraightMovePattern extends AbstractMovePattern {

    @Override
    public boolean canMove(Position source, Position destination) {
        return findDirection(source, destination) != null;
    }

    @Override
    public Direction findDirection(Position source, Position destination) {
        List<Direction> directions = getDirections();
        return directions.stream()
                .filter(direction -> source.canCrossMovingStraight(direction, destination))
                .findFirst()
                .orElse(null);
    }
}
