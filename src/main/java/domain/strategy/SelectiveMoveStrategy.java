package domain.strategy;

import static constants.Bound.BOARD_LOWER_BOUND;
import static constants.Bound.BOARD_UPPER_BOUND;

import domain.board.Board;
import domain.board.Position;
import domain.piece.Piece;
import domain.piece.info.Direction;
import java.util.ArrayList;
import java.util.List;

public class SelectiveMoveStrategy implements MoveStrategy {

    @Override
    public List<Position> movablePositions(final Position source, final List<Direction> directions, final Board board) {
        final List<Position> positions = new ArrayList<>();
        for (final Direction direction : directions) {
            positions.addAll(findMovablePositions(board, direction, source));
        }
        return positions;
    }

    private List<Position> findMovablePositions(final Board board, final Direction direction, final Position source) {
        final List<Position> positions = new ArrayList<>();
        Position current = source;
        Position next = getValidNextPosition(current, direction);

        while (isNextInboard(direction, current) && isNotPieceOnNextPosition(board, next, positions)) {
            next = current.next(direction);
            positions.add(next);
            current = next;
        }
        return positions;
    }

    private Position getValidNextPosition(final Position source, final Direction direction) {
        Position next = source;
        if (isNextInboard(direction, source)) {
            next = source.next(direction);
        }
        return next;
    }

    private boolean isNotPieceOnNextPosition(final Board board, final Position next, final List<Position> positions) {
        final Piece piece = board.findPieceByPosition(next);
        if (piece.isNotNone()) {
            positions.add(next);
            return false;
        }
        return true;
    }

    private boolean isNextInboard(final Direction direction, final Position current) {
        final int nextRank = direction.rank() + current.rankIndex();
        final int nextFile = direction.file() + current.fileIndex();
        return isNextRankInBoard(nextRank) && isNextFileInBoard(nextFile);
    }

    private boolean isNextRankInBoard(final int nextRank) {
        return nextRank >= BOARD_LOWER_BOUND.value() && nextRank <= BOARD_UPPER_BOUND.value();
    }

    private boolean isNextFileInBoard(final int nextFile) {
        return nextFile >= BOARD_LOWER_BOUND.value() && nextFile <= BOARD_UPPER_BOUND.value();
    }
}
