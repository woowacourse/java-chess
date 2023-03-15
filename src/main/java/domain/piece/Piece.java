package domain.piece;

import domain.piecetype.PieceType;

public interface Piece {

    PieceType getPieceType();
    Camp getCamp();
}
