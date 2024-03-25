package domain.strategy;

import static constants.Bound.BOARD_LOWER_BOUND;
import static constants.Bound.BOARD_UPPER_BOUND;

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
                .filter(direction -> isRankInBoard(source, direction))
                .filter(direction -> isFileInBoard(source, direction))
                .map(source::next)
                .toList();
    }

    private boolean isFileInBoard(final Position source, final Direction direction) {
        final int nextFile = direction.file() + source.fileIndex();
        return nextFile >= BOARD_LOWER_BOUND.value() && nextFile <= BOARD_UPPER_BOUND.value();
    }

    private boolean isRankInBoard(final Position source, final Direction direction) {
        final int nextRank = direction.rank() + source.rankIndex();
        return nextRank >= BOARD_LOWER_BOUND.value() && nextRank <= BOARD_UPPER_BOUND.value();
    }
}
