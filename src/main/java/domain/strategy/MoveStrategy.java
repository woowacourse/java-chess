package domain.strategy;

import domain.board.Position;
import domain.piece.info.Direction;
import java.util.List;

public interface MoveStrategy {
    List<Position> movablePositions(final Position source, final List<Direction> directions);
}
