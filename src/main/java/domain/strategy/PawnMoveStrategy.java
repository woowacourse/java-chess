package domain.strategy;

import domain.board.Position;
import domain.piece.Piece;
import domain.piece.info.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PawnMoveStrategy implements MoveStrategy {
    @Override
    public List<Position> movablePositions(final Position source, final List<Direction> directions,
                                           final Map<Position, Piece> pieces) {
        final List<Position> positions = new ArrayList<>();
        final List<Position> positionsPieceNotNone = findPositionsPieceNotNone(source, directions, pieces);
        final List<Position> positionsPieceNone = findPositionsPieceNone(source, directions, pieces);

        positions.addAll(positionsPieceNotNone);
        positions.addAll(positionsPieceNone);

        return positions.stream()
                .distinct()
                .toList();
    }

    private List<Position> findPositionsPieceNone(final Position source, final List<Direction> directions,
                                                  final Map<Position, Piece> pieces) {
        return directions.stream()
                .filter(direction -> isPieceOfPositionNone(source, direction, pieces))
                .filter(this::isNotDiagonalMovable)
                .map(source::next)
                .toList();
    }

    private List<Position> findPositionsPieceNotNone(final Position source, final List<Direction> directions,
                                                     final Map<Position, Piece> pieces) {
        return directions.stream()
                .filter(direction -> isMovableUpDown(source, direction, pieces))
                .map(source::next)
                .toList();
    }

    private boolean isPieceOfPositionNone(final Position source, final Direction direction,
                                          final Map<Position, Piece> pieces) {
        final Position next = source.next(direction);
        return !pieces.get(next).isNotNone();
    }

    private boolean isNotDiagonalMovable(final Direction direction) {
        return Stream.of(Direction.UP_LEFT,
                        Direction.UP_RIGHT,
                        Direction.DOWN_LEFT,
                        Direction.DOWN_RIGHT)
                .noneMatch(direction::equals);
    }

    private Boolean isMovableUpDown(final Position source, final Direction direction,
                                    final Map<Position, Piece> pieces) {
        final Piece otherPiece = pieces.get(source.next(direction));
        if ((isDirectionUpDown(direction) || isDirectionDoubleUpDown(direction) && otherPiece.isNotNone())) {
            return false;
        }
        return otherPiece.isNotNone() && pieces.get(source).color() != otherPiece.color();
    }

    private boolean isDirectionDoubleUpDown(final Direction direction) {
        return Direction.UP_UP == direction || Direction.DOWN_DOWN == direction;
    }

    private boolean isDirectionUpDown(final Direction direction) {
        return Direction.DOWN == direction || Direction.UP == direction;
    }
}
