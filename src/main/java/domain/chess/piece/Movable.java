package domain.chess.piece;

import domain.chess.Point;

import java.util.List;

public interface Movable {

    boolean canMove(Point point, List<Piece> pieceList);
}
