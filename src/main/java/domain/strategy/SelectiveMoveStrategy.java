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
            Position current = source;
            Position next = source;
            if (isNextInboard(direction, current)) {
                next = source.next(direction);
            }

            while (isNextInboard(direction, current) && isNotPieceOnNextPosition(board, next, positions)) {
                next = current.next(direction);
                positions.add(next);
                current = next;
            }
        }
        return positions;
    }

    private boolean isNotPieceOnNextPosition(final Board board, final Position position,
                                             final List<Position> positions) {
        final Piece piece = board.squares().get(position);
        if (piece.isNotNone()) {
            positions.add(position);
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
