package domain.strategy;

import domain.board.Position;
import domain.piece.Piece;
import domain.piece.info.Direction;
import java.util.List;
import java.util.Map;

public class AbsoluteMoveStrategy implements MoveStrategy {
    @Override
    public List<Position> movablePositions(final Position source, final List<Direction> directions,
                                           final Map<Position, Piece> pieces) {
        return findMovablePositions(source, directions);
    }

    private List<Position> findMovablePositions(final Position source, final List<Direction> directions) {
        return directions.stream()
                .map(source::next)
                .toList();
    }
}
