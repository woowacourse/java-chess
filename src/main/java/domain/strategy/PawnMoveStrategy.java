package domain.strategy;

import domain.board.Position;
import domain.piece.info.Direction;
import java.util.ArrayList;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    @Override
    public List<Position> movablePositions(final Position source, final List<Direction> directions) {
        List<Position> positions = new ArrayList<>();
        for (Direction direction : directions) {
            if (direction.rank() + source.rankIndex() < 0 || direction.rank() + source.rankIndex() > 7) {
                continue;
            }
            if (direction.file() + source.fileIndex() < 0 || direction.file() + source.fileIndex() > 7) {
                continue;
            }
            Position position = source.next(direction);
            positions.add(position);
        }
        return positions;
    }
}
