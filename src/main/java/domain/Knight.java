package domain;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Index;
import domain.piece.attribute.point.Point;

import java.util.List;

import static domain.piece.attribute.point.Direction.*;

public class Knight extends Piece {
    private static final List<List<Direction>> directionList = List.of(List.of(UP, UP_RIGHT),
            List.of(UP, UP_LEFT),
            List.of(UP, UP_RIGHT),
            List.of(LEFT, UP_LEFT),
            List.of(LEFT, DOWN_LEFT),
            List.of(RIGHT, UP_RIGHT),
            List.of(RIGHT, DOWN_RIGHT),
            List.of(DOWN, DOWN_RIGHT),
            List.of(DOWN, DOWN_LEFT));

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
