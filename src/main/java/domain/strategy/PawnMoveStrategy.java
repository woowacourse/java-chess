package domain.strategy;

import static constants.Bound.BOARD_LOWER_BOUND;
import static constants.Bound.BOARD_UPPER_BOUND;

import domain.board.Board;
import domain.board.Position;
import domain.piece.Piece;
import domain.piece.info.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PawnMoveStrategy implements MoveStrategy {
    @Override
    public List<Position> movablePositions(final Position source, final List<Direction> directions, final Board board) {
        final List<Position> positions = new ArrayList<>();
        final List<Position> positionsPieceNotNone = findPositionsPieceNotNone(source, directions, board);
        final List<Position> positionsPieceNone = findPositionsPieceNone(source, directions, board);

        positions.addAll(positionsPieceNotNone);
        positions.addAll(positionsPieceNone);

        return positions.stream()
                .distinct()
                .toList();
    }

    private List<Position> findPositionsPieceNone(final Position source, final List<Direction> directions,
                                                  final Board board) {
        return directions.stream()
                .filter(direction -> isNextRankInBoard(source, direction))
                .filter(direction -> isNextFileInBoard(source, direction))
                .filter(direction -> isPieceOfPositionNone(source, direction, board))
                .filter(this::isNotDiagonalMovable)
                .map(source::next)
                .toList();
    }

    private List<Position> findPositionsPieceNotNone(final Position source, final List<Direction> directions,
                                                     final Board board) {
        return directions.stream()
                .filter(direction -> isNextRankInBoard(source, direction))
                .filter(direction -> isNextFileInBoard(source, direction))
                .filter(direction -> isMovableUpDown(source, board, direction))
                .map(source::next)
                .toList();
    }

    private boolean isPieceOfPositionNone(final Position source, final Direction direction, final Board board) {
        final Position next = source.next(direction);
        return !board.squares().get(next).isNotNone();
    }

    private boolean isNotDiagonalMovable(final Direction direction) {
        return Stream.of(Direction.UP_LEFT,
                        Direction.UP_RIGHT,
                        Direction.DOWN_LEFT,
                        Direction.DOWN_RIGHT)
                .noneMatch(direction::equals);
    }

    private Boolean isMovableUpDown(final Position source, final Board board, final Direction direction) {
        final Piece otherPiece = board.squares().get(source.next(direction));
        if ((isDirectionUpDown(direction) || isDirectionDoubleUpDown(direction) && otherPiece.isNotNone())) {
            return false;
        }
        return otherPiece.isNotNone() && board.squares().get(source).color() != otherPiece.color();
    }

    private boolean isDirectionDoubleUpDown(final Direction direction) {
        return Direction.UP_UP == direction || Direction.DOWN_DOWN == direction;
    }

    private boolean isDirectionUpDown(final Direction direction) {
        return Direction.DOWN == direction || Direction.UP == direction;
    }

    private boolean isNextFileInBoard(final Position source, final Direction direction) {
        int nextFile = direction.file() + source.fileIndex();
        return nextFile >= BOARD_LOWER_BOUND.value() && nextFile <= BOARD_UPPER_BOUND.value();
    }

    private boolean isNextRankInBoard(final Position source, final Direction direction) {
        int nextRank = direction.rank() + source.rankIndex();
        return nextRank >= BOARD_LOWER_BOUND.value() && nextRank <= BOARD_UPPER_BOUND.value();
    }
}
