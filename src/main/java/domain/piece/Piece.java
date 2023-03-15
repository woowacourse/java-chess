package domain.piece;

import domain.piecetype.Coordinate;
import domain.piecetype.PieceType;

public interface Piece {

    PieceType getPieceType();
    Camp getCamp();
    Boolean isMovable(Coordinate startCoordinate, Coordinate endCoordinate);
    Boolean isExist();
}
