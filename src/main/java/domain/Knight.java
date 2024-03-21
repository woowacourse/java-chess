package domain;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Index;
import domain.piece.attribute.point.Point;

import java.util.List;

public class Knight extends Piece {
    public static final List<List<Direction>> directionList = List.of(List.of(Direction.UP, Direction.UP_RIGHT),
            List.of(Direction.UP, Direction.UP_LEFT),
            List.of(Direction.UP, Direction.UP_RIGHT),
            List.of(Direction.LEFT, Direction.UP_LEFT),
            List.of(Direction.LEFT, Direction.DOWN_LEFT),
            List.of(Direction.RIGHT, Direction.UP_RIGHT),
            List.of(Direction.RIGHT, Direction.DOWN_RIGHT),
            List.of(Direction.DOWN, Direction.DOWN_RIGHT),
            List.of(Direction.DOWN, Direction.DOWN_LEFT));

    public Knight(final Point point, final Color color) {
        super(point, color);
    }


    public boolean canMove(Point point) {
        return directionList.stream()
                            .map(this::moveIndex)
                            .filter(Index::isInBoundary)
                            .map(Point::fromIndex)
                            .anyMatch(point::equals);
    }

    private Index moveIndex(List<Direction> directions) {
        Index index = this.point.toIndex();
        for (final var direction : directions) {
            index = index.move(direction);
        }
        return index;
    }


    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KNIGHT;
    }
}
