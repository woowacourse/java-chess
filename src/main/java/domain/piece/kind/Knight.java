package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
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


    public boolean canMove(final Point point) {
        return directionList.stream()
                            .map(this::movePoint)
                            .anyMatch(point::equals);
    }

    //TODO : 테스틑 통과를 위한 임시 null ( 수정 되야하는 1순위 WORST )
    private Point movePoint(final List<Direction> directions) {
        Point point = this.point;
        for (final var direction : directions) {
            if (!direction.canMovePoint(point)) {
                return null;
            }
            point = direction.movePoint(point);
        }
        return point;
    }


    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KNIGHT;
    }
}
