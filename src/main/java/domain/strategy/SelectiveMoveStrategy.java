package domain.strategy;

import domain.board.Position;
import domain.piece.info.Direction;
import java.util.ArrayList;
import java.util.List;

public class SelectiveMoveStrategy implements MoveStrategy {

    @Override
    public List<Position> movablePositions(final Position source, final List<Direction> directions) {
        List<Position> positions = new ArrayList<>();
        for (Direction direction : directions) {
            Position current = source;
            while (true) {
                int nextRank = direction.rank() + current.rankIndex();
                int nextFile = direction.file() + current.fileIndex();
                if (nextRank < 0 || nextRank > 7) {
                    break;
                }
                if (nextFile < 0 || nextFile > 7) {
                    break;
                }
                Position position = current.next(direction);
                positions.add(position);
                current = position;
            }
        }
        return positions;
    }
}
