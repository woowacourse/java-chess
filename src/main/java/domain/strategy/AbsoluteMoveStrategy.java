package domain.strategy;

import domain.board.Board;
import domain.board.Position;
import domain.piece.info.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AbsoluteMoveStrategy implements MoveStrategy {
    @Override
    public List<Position> movablePositions(final Position source, final List<Direction> directions, final Board board) {
        return findMovablePositions(source, directions);
    }

    private List<Position> findMovablePositions(final Position source, final List<Direction> directions) {
        return directions.stream()
                .filter(direction -> isRankInBoard(source, direction))
                .filter(direction -> isFileInBoard(source, direction))
                .map(source::next)
                .collect(Collectors.toList());
    }

    private boolean isFileInBoard(final Position source, final Direction direction) {
        final int nextFile = direction.file() + source.fileIndex();
        return nextFile >= 0 && nextFile <= 7;
    }

    private boolean isRankInBoard(final Position source, final Direction direction) {
        final int nextRank = direction.rank() + source.rankIndex();
        return nextRank >= 0 && nextRank <= 7;
    }
}
