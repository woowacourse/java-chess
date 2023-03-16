package domain.square;

import domain.piece.Coordinate;
import domain.piece.Piece;

public interface Square {

    Piece getPieceType();
    Camp getCamp();
    Boolean isMovable(Coordinate startCoordinate, Coordinate endCoordinate);
    Boolean isExist();
    Boolean canReap();
}
