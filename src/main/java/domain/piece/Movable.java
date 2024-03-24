package domain.piece;

import domain.piece.attribute.point.Point;

import java.util.List;

public interface Movable {

    boolean canMove(Point point, List<Piece> pieceList);
}
