package domain;

import domain.piece.attribute.point.Point;

public interface Movable {

    boolean canMove(Point point);
}
